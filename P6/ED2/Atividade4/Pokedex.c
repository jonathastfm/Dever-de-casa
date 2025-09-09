
// Atividade 4 - Jonathas Marinho

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define TAM 11
#define VAZIO (-1)
#define APAGADO (-2)

typedef struct {
    int numero;
    char nome[50];
    char tipo[20];
} Pokemon;

// Funcao hash (usar resto da divisao)
int h(int chave) {
    return (chave % TAM);
}

// TODO: inicializar tabela (todo numero VAZIO)
void inicializar(Pokemon *tab) {
    for (int i = 0; i < TAM; i++) {
        tab[i].numero = VAZIO;
    }
}

// TODO: inserir usando sondagem linear
// retorno:
// 1 -> inseriu com sucesso
// 0 -> tabela cheia (falha)
// -1 -> ja existia, nao inseriu
int inserir(Pokemon *tab, int numero, const char *nome, const char *tipo) {
    int indiceInicial = h(numero);
    int i = 0;

    // Procura por duplicatas
    for (i = 0; i < TAM; i++) {
        int indice = (indiceInicial + i) % TAM;
        if (tab[indice].numero == numero) {
            return -1; // Ja existia
        }
        if (tab[indice].numero == VAZIO) {
            break;
        }
    }

    // Procura por uma posicao livre (VAZIO ou APAGADO)
    i = 0;
    while (i < TAM) {
        int indice = (indiceInicial + i) % TAM;
        if (tab[indice].numero == VAZIO || tab[indice].numero == APAGADO) {
            tab[indice].numero = numero;
            strcpy(tab[indice].nome, nome);
            strcpy(tab[indice].tipo, tipo);
            return 1; // Inserido com sucesso
        }
        i++;
    }

    return 0; // Tabela cheia
}

// TODO: buscar usando sondagem linear
// retorno:
// >=0 -> posicao do pokemon
// -1 -> nao encontrado
int buscar(Pokemon *tab, int numero) {
    int indiceInicial = h(numero);
    int i = 0;
    while (i < TAM) {
        int indice = (indiceInicial + i) % TAM;
        if (tab[indice].numero == numero) {
            return indice; // Pokemon encontrado
        }
        if (tab[indice].numero == VAZIO) {
            return -1; // Vazio, pode parar
        }
        i++;
    }
    return -1; // Nao encontrado apos percorrer a tabela
}

// TODO: remover (marcar como APAGADO)
int remover_pokemon(Pokemon *tab, int numero) {
    int indice = buscar(tab, numero);
    if (indice != -1) {
        tab[indice].numero = APAGADO;
        strcpy(tab[indice].nome, "");
        strcpy(tab[indice].tipo, "");
        return 1; // Removido com sucesso
    }
    return 0; // Nao encontrado para remover
}

// TODO: imprimir pokedex ocupada no formato: #num nome (tipo)
void imprimir_pokedex(Pokemon *tab) {
    printf("\n--- Pokemons na Pokedex ---\n");
    int count = 0;
    for (int i = 0; i < TAM; i++) {
        if (tab[i].numero != VAZIO && tab[i].numero != APAGADO) {
            printf("#%d %s (%s)\n", tab[i].numero, tab[i].nome, tab[i].tipo);
            count++;
        }
    }
    if (count == 0) {
        printf("Nenhum Pokemon na Pokedex.\n");
    }
    printf("-------------------------------\n");
}

// TODO: imprimir tabela (indices na 1a linha; numeros na 2a)
void imprimir_tabela(Pokemon *tab) {
    printf("\n--- Tabela Hash ---\n");
    printf("Indices: ");
    for (int i = 0; i < TAM; i++) {
        printf("%4d", i);
    }
    printf("\n");
    printf("Valores: ");
    for (int i = 0; i < TAM; i++) {
        printf("%4d", tab[i].numero);
    }
    printf("\n---------------------\n");
}

int main(void) {
    Pokemon tabela[TAM];
    inicializar(tabela);

    // Precarga de Pokemons
    inserir(tabela, 25, "Pikachu", "Eletrico");
    inserir(tabela, 1, "Bulbasaur", "Planta/Veneno");
    inserir(tabela, 4, "Charmander", "Fogo");
    inserir(tabela, 7, "Squirtle", "Agua");
    inserir(tabela, 26, "Raichu", "Eletrico");

    int opcao;
    int numero;
    char nome[50];
    char tipo[20];

    do {
        printf("\n=== MENU POKEDEX (HASH) ===\n");
        printf("1) Capturar Pokemon\n");
        printf("2) Buscar Pokemon por numero\n");
        printf("3) Remover Pokemon\n");
        printf("4) Listar Pokemons da Pokedex\n");
        printf("5) Imprimir Tabela Hash\n");
        printf("0) Sair\n");
        printf("Opcao: ");
        scanf("%d", &opcao);

        system("clear"); // ou system("cls") se usar windows
        switch (opcao) {
            case 1: {
                printf("\nInforme os dados do Pokemon abaixo.\n");
                printf("Numero: ");
                scanf("%d", &numero);
                printf("Nome: ");
                scanf("%s", nome);
                printf("Tipo: ");
                scanf("%s", tipo);
                int resultado = inserir(tabela, numero, nome, tipo);
                if (resultado == 1) {
                    printf("Pokemon capturado com sucesso!\n");
                } else if (resultado == 0) {
                    printf("Tabela cheia, nao foi possivel capturar.\n");
                } else {
                    printf("Pokemon ja existente na Pokedex.\n");
                }
                break;
            }
            case 2: {
                printf("Numero do Pokemon a ser buscado: ");
                scanf("%d", &numero);
                int resultado = buscar(tabela, numero);
                if (resultado != -1) {
                    printf("Pokemon encontrado na posicao %d: #%d %s (%s)\n",
                           resultado, tabela[resultado].numero, tabela[resultado].nome, tabela[resultado].tipo);
                } else {
                    printf("Pokemon nao encontrado na Pokedex.\n");
                }
                break;
            }
            case 3: {
                printf("Numero do Pokemon a ser removido: ");
                scanf("%d", &numero);
                int resultado = remover_pokemon(tabela, numero);
                if (resultado == 1) {
                    printf("Pokemon removido com sucesso.\n");
                } else {
                    printf("Pokemon nao encontrado para remocao.\n");
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

        // Limpa o buffer de entrada para evitar lixo
        int c;
        while ((c = getchar()) != '\n' && c != EOF); 
        printf("Pressione ENTER para continuar...");
        getchar();
        system("clear"); // ou system("cls") no Windows
    } while (opcao != 0);

    return 0;
}