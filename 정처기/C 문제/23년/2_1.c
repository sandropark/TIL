#include <stdio.h>

// 입력값이 순서대로 5, 4, 3, 2, 1일 경우 출력값이 43215로 출력 되도록 빈칸(가)에 들어갈 코드를 작성하시오.

int main()
{
    int n[5];
    int i;

    for (i = 0; i < 5; i++)
    {
        printf("숫자를 입력하세요 : ");
        scanf("%d", &n[i]);
    }

    for (i = 0; i < 5; i++)
    {
        printf("%d", (가)); // i = 12340
        // n[i + 1 == 5 ? 0 : i + 1]
        // n[(i + 1) % 5]
    }

    return 0;
}