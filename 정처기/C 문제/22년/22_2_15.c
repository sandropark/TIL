#include <stdio.h>

int len(char *p);

int main()
{

    char *p1 = "2022";   // 문자열
    char *p2 = "202207"; // 문자열

    int a = len(p1); // 4
    int b = len(p2); // 6

    printf("%d", a + b); // 10
}

int len(char *p)
{
    int r = 0;
    while (*p != '\0') // p의 값이 '\0'이 아닐 때까지
    {
        p++; // p의 값을 1증가. 배열을 순회하는 효과
        r++; // r의 값을 1증가
    }
    return r;
}