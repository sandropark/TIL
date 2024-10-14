#include <stdio.h>

int main(void)
{
    char str[] = "hello";
    printf("%s\n", str);

    char str_2[6] = "hello";
    printf("%s\n", str_2);

    char str_3[6] = {'h', 'e', 'l', 'l', 'o', '\0'};
    printf("%s\n", str_3);

    char str_4[] = {'h', 'e', 'l', 'l', 'o'};
    printf("%s\n", str_4);

    char *str_5 = "hello";
    printf("%s\n", str_5);
}