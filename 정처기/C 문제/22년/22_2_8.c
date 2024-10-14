#include <stdio.h>

struct A
{
    int n;
    int g;
};

int main()
{
    struct A a[2];
    for (int i = 0; i < 2; i++)
    {
        a[i].n = i, a[i].g = i + 1;
    }
    printf("%d", a[0].n + a[1].g); // 2
}

/*
a[0].n = 0
a[0].g = 1

a[1].n = 1
a[1].g = 2
*/
