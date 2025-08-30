#include <stdio.h>

int main() {
    float numero1;
    float numero2;
    float media;

    
    
    scanf( "%f", &numero1);
    

    
    scanf("%f", &numero2);
    

    media = ((numero1 * 3.5) + (numero2 * 7.5)) / 11;
    
    printf("MEDIA = %.5f\n", media);

    return 0;
}