// Alunos: [Seu nome]
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

/* Definição da struct Pokemon */
struct Pokemon {
    int numero;
    char nome[50];
    char tipo[20];
};

/* Funcao para imprimir a Pokedex */
void imprimirPokedex(struct Pokemon *pokedex, int n) {
    if (n == 0) {
        printf("--- A Pokedex esta vazia. ---\n\n");
        return;
    }
    printf("--- Pokedex Atual (Total: %d Pokemons) ---\n", n);
    for (int i = 0; i < n; i++) {
        printf("#%d %s (%s)\n", pokedex[i].numero, pokedex[i].nome, pokedex[i].tipo);
    }
    printf("--------------------------------------------\n\n");
}

/* Funcao de busca */
/* Retorna o indice do Pokemon se encontrado, ou -1 caso nao esteja na Pokedex. */
int busca(struct Pokemon *pokedex, int n, int numero) {
    for (int i = 0; i < n; i++) {
        if (pokedex[i].numero == numero) {
            return i; // Pokemon encontrado, retorna o indice
        }
    }
    return -1; // Pokemon nao encontrado
}

/* Funcao para adicionar um novo Pokemon */
void adicionarPokemon(struct Pokemon **pokedex, int *numPokemons) {
    int novoNumero;
    char novoNome[50];
    char novoTipo[20];

    printf("Digite o numero do novo Pokemon: ");
    scanf("%d", &novoNumero);

    printf("Digite o nome do novo Pokemon: ");
    scanf("%s", novoNome);

    printf("Digite o tipo do novo Pokemon: ");
    scanf("%s", novoTipo);

    // Expande a memoria para o novo Pokemon
    *numPokemons = *numPokemons + 1;
    *pokedex = (struct Pokemon *)realloc(*pokedex, (*numPokemons) * sizeof(struct Pokemon));
    if (*pokedex == NULL) {
        printf("Erro na realocacao de memoria.\n");
        exit(1);
    }

    // Adiciona o novo Pokemon na ultima posicao
    (*pokedex)[*numPokemons - 1].numero = novoNumero;
    strcpy((*pokedex)[*numPokemons - 1].nome, novoNome);
    strcpy((*pokedex)[*numPokemons - 1].tipo, novoTipo);

    printf("Pokemon %s adicionado com sucesso!\n\n", novoNome);
}

int main(void) {
    struct Pokemon *pokedex = NULL;
    int numPokemons = 0;
    int opcao;
    int numeroBusca;

    // Inicializa a pokedex com 3 pokemons para os requisitos do projeto
    numPokemons = 3;
    pokedex = (struct Pokemon *)malloc(numPokemons * sizeof(struct Pokemon));
    if (pokedex == NULL) {
        printf("Erro na alocacao de memoria inicial.\n");
        return 1;
    }

    pokedex[0].numero = 1;
    strcpy(pokedex[0].nome, "Bulbasaur");
    strcpy(pokedex[0].tipo, "Planta/Veneno");

    pokedex[1].numero = 4;
    strcpy(pokedex[1].nome, "Charmander");
    strcpy(pokedex[1].tipo, "Fogo");

    pokedex[2].numero = 7;
    strcpy(pokedex[2].nome, "Squirtle");
    strcpy(pokedex[2].tipo, "Agua");

    // Loop do menu
    do {
        printf("--- Menu Pokedex ---\n");
        printf("1. Visualizar Pokedex\n");
        printf("2. Adicionar Pokemon (Expandir Pokedex)\n");
        printf("3. Buscar Pokemon por numero\n");
        printf("4. Sair\n");
        printf("Escolha uma opcao: ");
        scanf("%d", &opcao);

        switch (opcao) {
            case 1:
                imprimirPokedex(pokedex, numPokemons);
                break;
            case 2:
                // Note que o projeto original pede para adicionar 2 pokemons de uma vez
                // mas essa funcao permite adicionar 1 por vez de forma interativa.
                // Voce pode chamar a funcao 2 vezes seguidas para atender ao requisito.
                adicionarPokemon(&pokedex, &numPokemons);
                adicionarPokemon(&pokedex, &numPokemons);
                break;
            case 3:
                printf("Digite o numero do Pokemon que deseja buscar: ");
                scanf("%d", &numeroBusca);
                int resultado = busca(pokedex, numPokemons, numeroBusca);
                if (resultado != -1) {
                    printf("Pokemon encontrado! #%d %s (%s)\n\n",
                           pokedex[resultado].numero, pokedex[resultado].nome, pokedex[resultado].tipo);
                } else {
                    printf("Pokemon com o numero %d nao encontrado na Pokedex.\n\n", numeroBusca);
                }
                break;
            case 4:
                printf("Saindo do programa. Ate mais!\n");
                break;
            default:
                printf("Opcao invalida. Por favor, escolha novamente.\n\n");
                break;
        }
    } while (opcao != 4);

    // Liberar a memoria antes de sair
    free(pokedex);
    pokedex = NULL;

    return 0;
}

