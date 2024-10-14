#include <stdio.h>

int func(int a)
{
    if (a <= 1)
        return 1;
    return a * func(a - 1); // 팩토리얼 함수 // a=3라면 3*2*1 = 6이 반환된다.
}

int main()
{
    int a;
    scanf("%d", &a);       // 입력값을 변수 a에 할당.
    printf("%d", func(a)); // a=5 : 120
}