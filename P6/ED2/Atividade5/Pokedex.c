#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// POKEDEX Tabela Hash com Lista Encadeada (Separate Chaining)

#define TAM 11 // numero primo ajuda na distribuicao

// Estrutura do dado armazenado (registro da Pokedex)
typedef struct {
    int numero; // chave (numero da Pokedex)
    char nome[50];
    char tipo[20];
} Pokemon;

// No da lista de cada bucket
typedef struct Node {
    Pokemon registro; // dado do no
    struct Node *next; // proximo no
} Node;

// Funcao hash
// O que fazer: calcular indice do bucket (0..TAM-1)
int h(int chave) {
    return (chave % TAM);
}

// Inicializacao da tabela
// O que fazer: colocar NULL em todas as posicoes do vetor
void inicializar(Node *tab[]) {
    for (int i = 0; i < TAM; i++) {
        tab[i] = NULL;
    }
}

// Buscar por numero
// O que fazer:
// - Calcular bucket com h(numero)
// - Percorrer lista encadeada
// - Se encontrar, retornar indice do bucket
// - Caso contrario, retornar -1
int buscar(Node *tab[], int numero) {
    int indice = h(numero);
    Node *p = tab[indice];
    while (p != NULL) {
        if (p->registro.numero == numero) {
            return indice; // Encontrado, retorna o indice do bucket
        }
        p = p->next;
    }
    return -1; // Nao encontrado
}

// Inserir novo registro
// O que fazer:
// - Se ja existir (buscar), retornar -1
// - Calcular indice com h(numero)
// - Alocar no (malloc)
// - Copiar numero, nome e tipo para novo->registro
// - Encadear no inicio da lista: novo->next = tab[idx]
// - tab[idx] = novo
// - Retornar 1 se deu certo; 0 se falhar
int inserir(Node *tab[], int numero, const char *nome, const char *tipo) {
    if (buscar(tab, numero) != -1) {
        return -1; // Ja existe
    }

    int indice = h(numero);
    Node *novo = (Node *)malloc(sizeof(Node));
    if (novo == NULL) {
        return 0; // Falha na alocacao
    }

    novo->registro.numero = numero;
    strcpy(novo->registro.nome, nome);
    strcpy(novo->registro.tipo, tipo);

    novo->next = tab[indice];
    tab[indice] = novo;

    return 1; // Inserido com sucesso
}

// Remover por numero
// O que fazer:
// - Calcular indice com h(numero)
// - Percorrer lista guardando anterior e atual
// - Ao encontrar, religar a lista e liberar no
// - Retornar 1 se removeu; 0 se nao achou
int remover_pokemon(Node *tab[], int numero) {
    int indice = h(numero);
    Node *anterior = NULL;
    Node *atual = tab[indice];

    while (atual != NULL && atual->registro.numero != numero) {
        anterior = atual;
        atual = atual->next;
    }

    if (atual == NULL) {
        return 0; // Pokemon nao encontrado
    }

    if (anterior == NULL) {
        // O no a ser removido e o primeiro da lista
        tab[indice] = atual->next;
    } else {
        // O no a ser removido nao e o primeiro
        anterior->next = atual->next;
    }

    free(atual);
    return 1; // Removido com sucesso
}

// Imprimir Pokedex (todos os registros)
// Implementacao fornecida
void imprimir_pokedex(Node *tab[]) {
    int vazio = 1;
    printf("\n--- Pokemons na Pokedex ---\n");
    for (int i = 0; i < TAM; i++) {
        for (Node *p = tab[i]; p != NULL; p = p->next) {
            printf("#%d %s (%s)\n", p->registro.numero, p->registro.nome, p->registro.tipo);
            vazio = 0;
        }
    }
    if (vazio) {
        printf("(Pokedex vazia)\n");
    }
    printf("-------------------------------\n");
}

// Imprimir Tabela (cadeias por bucket)
// Implementacao fornecida
void imprimir_tabela(Node *tab[]) {
    printf("\n--- Tabela Hash ---\n");
    for (int i = 0; i < TAM; i++) {
        printf("[%d]: ", i);
        if (!tab[i]) {
            printf("-\n");
            continue;
        }
        Node *p = tab[i];
        while (p) {
            printf("%d", p->registro.numero);
            p = p->next;
            if (p) {
                printf(" -> ");
            }
        }
        printf("\n");
    }
    printf("---------------------\n");
}

int main(void) {
    Node *tabela[TAM];
    inicializar(tabela);

    // Precarga de Pokemons
    inserir(tabela, 25, "Pikachu", "Eletrico");
    inserir(tabela, 1, "Bulbasaur", "Planta/Veneno");
    inserir(tabela, 4, "Charmander", "Fogo");
    inserir(tabela, 7, "Squirtle", "Agua");
    inserir(tabela, 15, "Beedrill", "Inseto/Veneno");
    inserir(tabela, 18, "Pidgeot", "Normal/Voador");
    inserir(tabela, 29, "Nidoran-f", "Veneno");
    
    int opcao;
    int numero;
    char nome[50];
    char tipo[20];
    int r;
    int pos;

    do {
        printf("\n=== MENU POKEDEX (HASH + LISTA) ===\n");
        printf("1) Capturar Pokemon\n");
        printf("2) Buscar Pokemon por numero\n");
        printf("3) Remover Pokemon\n");
        printf("4) Listar Pokemons da Pokedex\n");
        printf("5) Imprimir Tabela Hash (cadeias)\n");
        printf("0) Sair\n");
        printf("Opcao: ");
        
        if (scanf("%d", &opcao) != 1) {
            opcao = -1; // Opcao invalida
        }
        // Limpa o buffer de entrada após ler a opção
        int ch;
        while ((ch = getchar()) != '\n' && ch != EOF);

        switch (opcao) {
            case 1: {
                printf("\nInforme os dados do Pokemon abaixo.\n");
                printf("Numero: ");
                scanf("%d", &numero);
                
                // Limpa o buffer antes de ler strings
                while ((ch = getchar()) != '\n' && ch != EOF);
                
                printf("Nome: ");
                fgets(nome, sizeof(nome), stdin);
                nome[strcspn(nome, "\n")] = '\0';
                
                printf("Tipo: ");
                fgets(tipo, sizeof(tipo), stdin);
                tipo[strcspn(tipo, "\n")] = '\0';
                
                r = inserir(tabela, numero, nome, tipo);
                if (r == 1) {
                    printf("Inserido com sucesso.\n");
                } else if (r == -1) {
                    printf("Ja existe esse numero na Pokedex.\n");
                } else {
                    printf("Falha ao inserir.\n");
                }
                break;
            }
            case 2: {
                printf("Numero do Pokemon a ser buscado: ");
                scanf("%d", &numero);
                pos = buscar(tabela, numero);
                if (pos >= 0) {
                    printf("Encontrado no bucket %d.\n", pos);
                } else {
                    printf("Nao encontrado.\n");
                }
                break;
            }
            case 3: {
                printf("Numero do Pokemon a ser removido: ");
                scanf("%d", &numero);
                if (remover_pokemon(tabela, numero)) {
                    printf("Removido.\n");
                } else {
                    printf("Nao encontrado.\n");
                }
                break;
            }
            case 4:
                imprimir_pokedex(tabela);
                break;
            case 5:
                imprimir_tabela(tabela);
                break;
            case 0:
                printf("Saindo...\n");
                break;
            default:
                printf("Opcao invalida.\n");
                break;
        }

        if (opcao != 0) {
            printf("\nPressione ENTER para continuar...");
            while ((ch = getchar()) != '\n' && ch != EOF);
            getchar();
            system("cls"); // ou system("cls") para Windows
        }
    } while (opcao != 0);

    // Liberacao de memoria
    for (int i = 0; i < TAM; i++) {
        Node *atual = tabela[i];
        while (atual != NULL) {
            Node *proximo = atual->next;
            free(atual);
            atual = proximo;
        }
    }

    return 0;
}