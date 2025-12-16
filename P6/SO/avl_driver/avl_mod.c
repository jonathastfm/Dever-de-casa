#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/slab.h>
#include <linux/proc_fs.h>
#include <linux/seq_file.h>
#include <linux/uaccess.h>
#include <linux/list.h>

MODULE_LICENSE("GPL");
MODULE_AUTHOR("Copilot");
MODULE_DESCRIPTION("AVL Tree Driver with Memory Logging");

#define PROC_FILENAME "avl_tree"

// --- Estrutura de Log ---
struct avl_log {
    char message[256];
    struct list_head list;
};

static LIST_HEAD(log_list);
static DEFINE_MUTEX(log_mutex);

void add_log(const char *fmt, ...) {
    struct avl_log *new_log;
    va_list args;

    new_log = kmalloc(sizeof(*new_log), GFP_KERNEL);
    if (!new_log) {
        printk(KERN_ERR "AVL: Falha ao alocar memoria para log\n");
        return;
    }

    va_start(args, fmt);
    vsnprintf(new_log->message, sizeof(new_log->message), fmt, args);
    va_end(args);

    mutex_lock(&log_mutex);
    list_add_tail(&new_log->list, &log_list);
    mutex_unlock(&log_mutex);
}

void clear_logs(void) {
    struct avl_log *log, *tmp;
    mutex_lock(&log_mutex);
    list_for_each_entry_safe(log, tmp, &log_list, list) {
        list_del(&log->list);
        kfree(log);
    }
    mutex_unlock(&log_mutex);
}

// --- Estrutura da Arvore AVL ---
typedef struct Node {
    int key;
    struct Node *left;
    struct Node *right;
    int height;
} Node;

static Node *root = NULL;

int height(Node *N) {
    if (N == NULL)
        return 0;
    return N->height;
}

int max_int(int a, int b) {
    return (a > b) ? a : b;
}

Node* newNode(int key) {
    Node* node = (Node*)kmalloc(sizeof(Node), GFP_KERNEL);
    if (!node) {
        add_log("ERRO: Falha de alocacao de memoria para chave %d", key);
        return NULL;
    }
    node->key = key;
    node->left = NULL;
    node->right = NULL;
    node->height = 1;
    
    add_log("MEMORIA: No alocado em %p com chave %d", node, key);
    return(node);
}

Node *rightRotate(Node *y) {
    Node *x = y->left;
    Node *T2 = x->right;

    x->right = y;
    y->left = T2;

    y->height = max_int(height(y->left), height(y->right)) + 1;
    x->height = max_int(height(x->left), height(x->right)) + 1;

    add_log("ROTACAO: Direita em torno do no %d", y->key);
    return x;
}

Node *leftRotate(Node *x) {
    Node *y = x->right;
    Node *T2 = y->left;

    y->left = x;
    x->right = T2;

    x->height = max_int(height(x->left), height(x->right)) + 1;
    y->height = max_int(height(y->left), height(y->right)) + 1;

    add_log("ROTACAO: Esquerda em torno do no %d", x->key);
    return y;
}

int getBalance(Node *N) {
    if (N == NULL)
        return 0;
    return height(N->left) - height(N->right);
}

Node* insert(Node* node, int key) {
    int balance;
    
    if (node == NULL)
        return(newNode(key));

    if (key < node->key)
        node->left = insert(node->left, key);
    else if (key > node->key)
        node->right = insert(node->right, key);
    else
        return node;

    node->height = 1 + max_int(height(node->left), height(node->right));

    balance = getBalance(node);

    // Left Left Case
    if (balance > 1 && key < node->left->key)
        return rightRotate(node);

    // Right Right Case
    if (balance < -1 && key > node->right->key)
        return leftRotate(node);

    // Left Right Case
    if (balance > 1 && key > node->left->key) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    // Right Left Case
    if (balance < -1 && key < node->right->key) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

void free_tree(Node *node) {
    if (node == NULL) return;
    free_tree(node->left);
    free_tree(node->right);
    add_log("MEMORIA: Liberando no em %p (chave %d)", node, node->key);
    kfree(node);
}

// --- Interface ProcFS ---

static int avl_proc_show(struct seq_file *m, void *v) {
    struct avl_log *log;
    
    seq_printf(m, "--- Logs da Arvore AVL ---\n");
    
    mutex_lock(&log_mutex);
    list_for_each_entry(log, &log_list, list) {
        seq_printf(m, "%s\n", log->message);
    }
    mutex_unlock(&log_mutex);
    
    return 0;
}

static int avl_proc_open(struct inode *inode, struct file *file) {
    return single_open(file, avl_proc_show, NULL);
}

static ssize_t avl_proc_write(struct file *file, const char __user *buffer, size_t count, loff_t *pos) {
    char *kbuffer;
    int key;

    if (count > 32) return -EINVAL;

    kbuffer = kmalloc(count + 1, GFP_KERNEL);
    if (!kbuffer) return -ENOMEM;

    if (copy_from_user(kbuffer, buffer, count)) {
        kfree(kbuffer);
        return -EFAULT;
    }
    kbuffer[count] = '\0';

    if (kstrtoint(kbuffer, 10, &key) == 0) {
        add_log("COMANDO: Inserindo chave %d", key);
        root = insert(root, key);
    } else {
        add_log("ERRO: Comando invalido recebido: %s", kbuffer);
    }

    kfree(kbuffer);
    return count;
}

static const struct proc_ops avl_proc_ops = {
    .proc_open = avl_proc_open,
    .proc_read = seq_read,
    .proc_write = avl_proc_write,
    .proc_lseek = seq_lseek,
    .proc_release = single_release,
};

// --- Inicializacao do Modulo ---

static int __init avl_init(void) {
    struct proc_dir_entry *entry;
    
    printk(KERN_INFO "AVL: Modulo carregado.\n");
    add_log("SISTEMA: Modulo AVL iniciado");

    entry = proc_create(PROC_FILENAME, 0666, NULL, &avl_proc_ops);
    if (!entry) {
        return -ENOMEM;
    }

    // Insercao inicial para teste
    root = insert(root, 10);
    root = insert(root, 20);
    root = insert(root, 30); // Deve causar rotacao
    root = insert(root, 40);
    root = insert(root, 50);
    root = insert(root, 25);

    return 0;
}

static void __exit avl_exit(void) {
    remove_proc_entry(PROC_FILENAME, NULL);
    free_tree(root);
    clear_logs();
    printk(KERN_INFO "AVL: Modulo descarregado.\n");
}

module_init(avl_init);
module_exit(avl_exit);
