#include <stdio.h>

int main()
{
    int s, el = 0;
    for (int i = 6; i <= 30; i++) // 6~30
    {
        s = 0;
        for (int j = 1; j <= i / 2; j++) // 1 ~ i/2
        {
            if (i % j == 0)
            {
                s = s + j;
            }
        }
        if (s == i)
        {
            el++;
        }
    }
    printf("%d", el);
}