#include <stdio.h>
#include <stdlib.h>

// Estrutura do no da arvore
typedef struct Node {
    int key;
    struct Node *left;
    struct Node *right;
    int height;
} Node;

// Funcao utilitaria para obter a altura da arvore
int height(Node *N) {
    if (N == NULL)
        return 0;
    return N->height;
}

// Funcao utilitaria para obter o maximo de dois inteiros
int max_int(int a, int b) {
    return (a > b) ? a : b;
}
 
// Funcao auxiliar para alocar um novo no
Node* newNode(int key) {
    Node* node = (Node*)malloc(sizeof(Node));
    node->key = key;
    node->left = NULL;
    node->right = NULL;
    node->height = 1; // Novo no e inicialmente adicionado na folha
    return(node);
}

// Rotacao a direita (Right Rotate)
Node *rightRotate(Node *y) {
    Node *x = y->left;
    Node *T2 = x->right;

    // Realiza a rotacao
    x->right = y;
    y->left = T2;

    // Atualiza as alturas
    y->height = max_int(height(y->left), height(y->right)) + 1;
    x->height = max_int(height(x->left), height(x->right)) + 1;

    // Retorna a nova raiz
    return x;
}

// Rotacao a esquerda (Left Rotate)
Node *leftRotate(Node *x) {
    Node *y = x->right;
    Node *T2 = y->left;

    // Realiza a rotacao
    y->left = x;
    x->right = T2;

    // Atualiza as alturas
    x->height = max_int(height(x->left), height(x->right)) + 1;
    y->height = max_int(height(y->left), height(y->right)) + 1;

    // Retorna a nova raiz
    return y;
}

// Obtem o fator de balanceamento do no N
int getBalance(Node *N) {
    if (N == NULL)
        return 0;
    return height(N->left) - height(N->right);
}

// Funcao recursiva para inserir uma chave na subarvore enraizada com node
Node* insert(Node* node, int key) {
    // 1. Insercao normal de BST
    if (node == NULL)
        return(newNode(key));

    if (key < node->key)
        node->left = insert(node->left, key);
    else if (key > node->key)
        node->right = insert(node->right, key);
    else // Chaves iguais nao sao permitidas na BST padrao
        return node;

    // 2. Atualiza a altura deste no ancestral
    node->height = 1 + max_int(height(node->left), height(node->right));

    // 3. Obtem o fator de balanceamento para verificar se ficou desbalanceado
    int balance = getBalance(node);

    // Se o no ficar desbalanceado, existem 4 casos:

    // Caso Esquerda-Esquerda (Left Left Case)
    if (balance > 1 && key < node->left->key)
        return rightRotate(node);

    // Caso Direita-Direita (Right Right Case)
    if (balance < -1 && key > node->right->key) {
        return leftRotate(node);
    }

    // Caso Esquerda-Direita (Left Right Case)
    if (balance > 1 && key > node->left->key) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    // Caso Direita-Esquerda (Right Left Case)
    if (balance < -1 && key < node->right->key) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    // Retorna o ponteiro do no (inalterado)
    return node;
}

// Funcao para encontrar o no com o menor valor (usado na remocao)
Node * minValueNode(Node* node) {
    Node* current = node;
    while (current->left != NULL)
        current = current->left;
    return current;
}

// Funcao recursiva para deletar um no e rebalancear a arvore
Node* deleteNode(Node* root, int key) {
    // 1. Delecao padrao de BST
    if (root == NULL)
        return root;

    if (key < root->key)
        root->left = deleteNode(root->left, key);
    else if (key > root->key)
        root->right = deleteNode(root->right, key);
    else {
        // No com apenas um filho ou sem filhos
        if ((root->left == NULL) || (root->right == NULL)) {
            Node *temp = root->left ? root->left : root->right;

            // Caso sem filhos
            if (temp == NULL) {
                temp = root;
                root = NULL;
            } else // Caso um filho
                *root = *temp; // Copia o conteudo do filho nao vazio

            free(temp);
        } else {
            // No com dois filhos: obtem o sucessor em ordem (menor na subarvore direita)
            Node* temp = minValueNode(root->right);

            // Copia a chave do sucessor para este no
            root->key = temp->key;

            // Deleta o sucessor
            root->right = deleteNode(root->right, temp->key);
        }
    }

    // Se a arvore tinha apenas um no, entao retorna
    if (root == NULL)
        return root;

    // 2. Atualiza a altura do no atual
    root->height = 1 + max_int(height(root->left), height(root->right));

    // 3. Obtem o fator de balanceamento
    int balance = getBalance(root);

    // 4. Se o no ficar desbalanceado, existem 4 casos

    // Caso Esquerda-Esquerda
    if (balance > 1 && getBalance(root->left) >= 0)
        return rightRotate(root);

    // Caso Esquerda-Direita
    if (balance > 1 && getBalance(root->left) < 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    // Caso Direita-Direita
    if (balance < -1 && getBalance(root->right) <= 0)
        return leftRotate(root);

    // Caso Direita-Esquerda
    if (balance < -1 && getBalance(root->right) > 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

// Funcao para imprimir a arvore no terminal de forma visual
// space: distancia da margem esquerda
// COUNT: distancia entre niveis
#define COUNT 10

void printTreeUtil(Node *root, int space) {
    // Caso base
    if (root == NULL)
        return;

    // Aumenta a distancia entre os niveis
    space += COUNT;

    // Processa o filho da direita primeiro (para ficar no topo visualmente)
    printTreeUtil(root->right, space);

    // Imprime o no atual apos o espacamento
    printf("\n");
    for (int i = COUNT; i < space; i++)
        printf(" ");
    printf("%d\n", root->key);

    // Processa o filho da esquerda
    printTreeUtil(root->left, space);
}

// Wrapper para a funcao de impressao
void printTree(Node *root) {
    printf("--------------------------------------------------\n");
    printf("Visualizacao da Arvore AVL (Raiz a esquerda):\n");
    printf("--------------------------------------------------\n");
    printTreeUtil(root, 0);
    printf("--------------------------------------------------\n");
}

// Funcao para exibir o menu e obter a escolha do usuario
void displayMenu() {
    printf("\n--- Menu AVL ---\n");
    printf("1. Adicionar no\n");
    printf("2. Remover no\n");
    printf("3. Imprimir arvore\n");
    printf("4. Sair\n");
    printf("Escolha uma opcao: ");
}


int main() {
    Node *root = NULL;
    int choice, key;

    do {
        displayMenu();
        scanf("%d", &choice);

        switch (choice) {
            case 1:
                printf("Digite o valor a ser inserido: ");
                scanf("%d", &key);
                root = insert(root, key);
                printf("No %d inserido.\n", key);
                break;
            case 2:
                printf("Digite o valor a ser removido: ");
                scanf("%d", &key);
                root = deleteNode(root, key);
                printf("No %d removido (se existia).\n", key);
                break;
            case 3:
                if (root == NULL) {
                    printf("A arvore esta vazia.\n");
                } else {
                    printTree(root);
                    printf("Altura da raiz: %d\n", height(root));
                }
                break;
            case 4:
                printf("Saindo...\n");
                break;
            default:
                printf("Opcao invalida. Tente novamente.\n");
        }
    } while (choice != 4);

    // Liberar a memoria da arvore antes de sair (opcional, para um programa mais robusto)
    // freeTree(root); // Seria necessario implementar uma funcao freeTree

    return 0;
}
