#include <stdio.h>

int main(void)
{
    int c = 1;
    switch (3)
    {
    case 1:
        c += 3;
    case 2:
        c++;
    case 3:
        // 여기로 들어와서 0이 되고 break가 없어서 아래로 내려가면서 계속 연산된다.
        c = 0;
    case 4:
        c += 3;
    case 5:
        c -= 10;
    default:
        c--;
    }
    printf("%d", c); // -8
}