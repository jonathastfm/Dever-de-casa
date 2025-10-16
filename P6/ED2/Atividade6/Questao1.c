#include <stdlib.h>
#include <stddef.h>
#include <stdio.h>

struct Node
{
    int data;
    struct Node* left;
    struct Node* right;
};

struct Node* newNode(int data)
{
    struct Node* node = (struct Node*)malloc(sizeof(struct Node));
    node->data = data;
    node->left = NULL;
    node->right = NULL;
    return node;
}

void ImprimePreOrdem(struct Node* node)
{
    if (node == NULL)
        return;

    // Print the data of the node
    printf("%d ", node->data);

    // Traverse the left subtree
    ImprimePreOrdem(node->left);

    // Traverse the right subtree
    ImprimePreOrdem(node->right);
}

void ImprimeEmOrdem(struct Node* node)
{
    if (node == NULL)
        return;

    // Traverse the left subtree
    ImprimeEmOrdem(node->left);

    // Print the data of the node
    printf("%d ", node->data);

    // Traverse the right subtree
    ImprimeEmOrdem(node->right);
}

ImprimePosOrdem(struct Node* node)
{
    if (node == NULL)
        return;

    // Traverse the left subtree
    ImprimePosOrdem(node->left);

    // Traverse the right subtree
    ImprimePosOrdem(node->right);

    // Print the data of the node
    printf("%d ", node->data);
}

struct Node* insert(struct Node* node, int data)
{
    if (node == NULL)
        return newNode(data);

    if (data < node->data)
        node->left = insert(node->left, data);
    else
        node->right = insert(node->right, data);

    return node;
};

struct Node* search(struct Node* node, int data)
{
    if (node == NULL || node->data == data)
        return node;

    if (data < node->data)
        return search(node->left, data);
    else
        return search(node->right, data);
};

struct Node* minSubLeft(struct Node* node)
{
    if (node == NULL)
        return NULL;

    struct Node* current = node;
    while (current->left != NULL) {
        current = current->left;
    }
    return current;
};

struct Node* minSubRight(struct Node* node)
{
    if (node == NULL)
        return NULL;

    struct Node* current = node;
    while (current->right != NULL) {
        current = current->right;
    }
    return current;
};

int main()
{
    struct Node* root = NULL;

    
    int valores[] = {50, 30, 40, 70, 60, 80, 65, 43, 15};
    int n = sizeof(valores) / sizeof(valores[0]);
    for (int i = 0; i < n; i++) {
        root = insert(root, valores[i]);
    }

    printf("Pre-Ordem: ");
    ImprimePreOrdem(root);
    printf("\n");

    printf("Em-Ordem: ");
    ImprimeEmOrdem(root);
    printf("\n");

    printf("Pos-Ordem: ");
    ImprimePosOrdem(root);
    printf("\n");
}
