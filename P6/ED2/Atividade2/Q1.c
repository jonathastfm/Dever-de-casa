#include <stdio.h>

void trocar(int *a, int *b) {
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main(void) {
    int x = 10;
    int y = 20;

    printf("Antes da troca: x = %d, y = %d\n", x, y);

    // Chama a função trocar, passando os endereços de memória de x e y
    trocar(&x, &y);

    printf("Depois da troca: x = %d, y = %d\n", x, y);

    return 0;
}