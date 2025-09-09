#include <stdio.h>

int main() {
    int total_days;
    scanf("%d", &total_days);

    int years = total_days / 365;
    int remaining_days = total_days % 365;

    int months = remaining_days / 30;
    int days = remaining_days % 30;

    printf("%d ano(s)\n", years);
    printf("%d mes(es)\n", months);
    printf("%d dia(s)\n", days);

    return 0;
}