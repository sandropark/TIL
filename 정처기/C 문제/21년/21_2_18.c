#include <stdio.h>

int main()
{
    int ary[3];
    int s = 0;
    *(ary + 0) = 1;          // 배열의 처음 요소로 1을 할당.
    ary[1] = *(ary + 0) + 2; // 배열의 두번째 요소로 3(1+2)을 할당
    ary[2] = *ary + 3;       // 배열의 세번째 요소로 4(1+3)을 할당
    for (int i = 0; i < 3; i++)
    {
        s = s + ary[i]; // 배열의 모든 값을 더하기
    }

    printf("%d", s); // 8
}