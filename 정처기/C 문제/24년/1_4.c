#include <stdio.h>
#include <string.h>

void reverse(char *str)
{
    int len = strlen(str); // 8
    char temp;
    char *p1 = str;           // 시작 주소
    char *p2 = str + len - 1; // 끝 주소
    while (p1 < p2)
    {
        temp = *p1; // p1의 값을 할당
        *p1 = *p2;
        *p2 = temp;
        p1++; // 시작 주소++
        p2--; // 끝 주소--
    }
}

int main(int argc, char *argv[])
{
    char str[100] = "ABCDEFGH"; // len = 8

    reverse(str); // HGFEDCBA

    int len = strlen(str); // 8

    for (int i = 1; i < len; i += 2) // 1, 3, 5, 7
    {
        printf("%c", str[i]); // GECA
    }

    printf("\n");

    return 0;
}