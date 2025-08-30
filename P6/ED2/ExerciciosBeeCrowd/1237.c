#include <stdio.h>
#include <string.h>

int main() {
    char s1[51], s2[51];
    
    while (fgets(s1, sizeof(s1), stdin) != NULL && fgets(s2, sizeof(s2), stdin) != NULL) {
        
        // Remove trailing newline characters from input
        s1[strcspn(s1, "\n")] = 0;
        s2[strcspn(s2, "\n")] = 0;
        
        int m = strlen(s1);
        int n = strlen(s2);
        
        int dp[m + 1][n + 1];
        int max_length = 0;

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (s1[i - 1] == s2[j - 1]) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > max_length) {
                        max_length = dp[i][j];
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        printf("%d\n", max_length);
    }
    return 0;
}