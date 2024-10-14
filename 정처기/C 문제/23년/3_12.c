#include <stdio.h>

int main()
{
    int *arr[3]; // 주소를 값으로 가지는 배열 변수를 선언
    int a = 12, b = 24, c = 36;
    // 배열의 값에 주소를 할당
    arr[0] = &a;
    arr[1] = &b;
    arr[2] = &c;

    printf("%d\n", *arr[1] + **arr + 1); // 37
    // *arr[1] = 두번째 요소의 값(24)
    // **arr = 배열의 첫번째 주소의 값 = a주소. a주소의 값 = 12
}