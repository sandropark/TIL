#include <stdio.h>

int f(int n)
{
    if (n <= 1)
        return 1;
    else
        return n * f(n - 1);
}

int main()
{
    printf("%d", f(7)); // 7*6*5*4*3*2 = 5040
}