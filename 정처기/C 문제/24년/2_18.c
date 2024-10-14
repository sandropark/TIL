#include <stdio.h>

void swap(int a, int b) // 주소가 아니라 값이기 때문에 swap되지 않는다.
{
    int t = a;
    a = b;
    b = t;
}

int main()
{
    int a = 11;
    int b = 19;
    swap(a, b); // 11, 19

    switch (a)
    {
    case 1:
        b += 1;
    case 11:
        b += 2; // b=21
    default:
        b += 3; // b=24
        break;
    }

    printf("%d", a - b); // -13
}