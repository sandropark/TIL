#include <stdio.h>

int mp(int base, int exp)
{
    int res = 1;
    for (int i = 0; i < exp; i++)
    {
        res *= base;
    }

    return res;
}

int main(void)
{
    int res;
    res = mp(2, 10);
    printf("%d", res); // 1024
    return 0;
}