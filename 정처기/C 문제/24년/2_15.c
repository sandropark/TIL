#include <stdio.h>
#include <string.h>

void sumFn(char *d, char *s) // 주소, 주소 - teststring, first
{
    int sum = 0;

    while (*s)
    {
        *d = *s; // 처음 주소의 값을 d의 처음 주소의 값에 할당 -> *d = 'f'
        d++;
        s++;
    }
    *d = '\0';
}

int main()
{
    char *str1 = "first";
    char str2[50] = "teststring";
    int result = 0;
    sumFn(str2, str1); // str2 -> firsttring, str1 -> first

    for (int i = 0; str2[i] != '\0'; i++) // str2 == firsttring
    {
        result += i;
    }
    printf("%d", result); // 10

    return 0;
}