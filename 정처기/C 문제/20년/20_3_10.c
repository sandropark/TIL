#include <stdio.h>
int r1(void)
{
    return 4;
}

int r10(void)
{
    return (30 + r1());
}

int r100(void)
{
    return (200 + r10());
}

int main(void)
{
    printf("%d\n", r100()); // 234
}