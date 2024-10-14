#include <stdio.h>

// 1부터 2023 중 4의 배수의 개수

int main(void)
{
    int c = 0;
    for (int i = 1; i <= 2023; i++) // 1 ~ 2023
    {
        if (i % 4 == 0) // 4 * 500 = 2000
            c++;
    }
    printf("%d", c); // 505
    return 0;
}