#include <stdio.h>

int main(void)
{
    char a[] = "Art";
    char *p = NULL;
    p = a;              // &a[0]
    printf("%s\n", a);  // Art
    printf("%c\n", *p); // A
    printf("%c\n", *a); // A
    printf("%s\n", a);  // Art

    for (int i = 0; a[i] != '\0'; i++)
    {
        printf("%c", a[i]); // Art
    }
}