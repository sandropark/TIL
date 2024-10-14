#include <stdio.h>

int main(void)
{
    // int 자료형의 크기는 4byte
    int E[] = {64, 25, 12, 22, 11};
    int n = sizeof(E) / sizeof(E[0]); // (sizeof(E)=20) / (sizeof(E[0])=4) = 5 요소의 개수

    int i = 0;
    do
    {
        int j = i + 1;
        do
        {
            if (E[i](가) E[j]) // >
            {
                int tmp = E[i];
                E[i] = E[j];
                E[j] = tmp;
            }
            j++;

        } while (j < n);

        i++;
    } while (i < n - 1);

    for (int idx = 0; idx < n; idx++)
    {
        printf("%d\n", E[idx]);
    }

    return 0;
}