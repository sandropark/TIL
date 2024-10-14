#include <stdio.h>

int main(int argc, char *argv[])
{
    int a[4] = {0, 2, 4, 8};
    int b[3] = {};
    int i = 1;
    int sum = 0;
    int *p1;

    for (i; i < 4; i++) // 1부터 3까지 반복
    {
        p1 = a + i;
        b[i - 1] = *p1 - a[i - 1];
        sum = sum + b[i - 1] + a[i];
    }
    printf("%d", sum); // 22

    return 0;
}

/*
1번
p1 = a + i;  i=1임으로 a의 두번째 요소(2)의 주소를 할당
b[i - 1] = *p1 - a[i - 1];  b의 첫번째 요소로 p1의 값(2) - a[0](0) = 2를 할당 b = {2}
sum = sum + b[i - 1] + a[i]; 0 + 2 + 2 = 4를 할당

2번
p1 = a + i;  i=2임으로 a의 세번째 요소(4)의 주소를 할당
b[i - 1] = *p1 - a[i - 1];  b의 두번째 요소로 p1의 값(4) - a[1](2) = 2를 할당 b = {2, 2}
sum = sum + b[i - 1] + a[i];  4 + 2 + 4 = 10

2번
p1 = a + i;  i=3임으로 a의 네번째 요소(8)의 주소를 할당
b[i - 1] = *p1 - a[i - 1];  b의 세번째 요소로 p1의 값(8) - a[2](4) = 4를 할당 b = {2, 2, 4}
sum = sum + b[i - 1] + a[i];  10 + 4 + 8 = 22

*/