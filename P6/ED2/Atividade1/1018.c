#include <stdio.h>

int main() {
    int N;
    scanf("%d", &N);

    printf("%d\n", N);

    int notes[7] = {100, 50, 20, 10, 5, 2, 1};
    int current_N = N;

    for (int i = 0; i < 7; i++) {
        int count = current_N / notes[i];
        current_N = current_N % notes[i];
        printf("%d nota(s) de R$ %d,00\n", count, notes[i]);
    }

    return 0;
}