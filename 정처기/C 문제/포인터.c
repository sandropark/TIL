#include <stdio.h>

int main(void)
{
    int 암호 = 1;
    printf("암호 주소 : %d, 암호 : %d\n", &암호, 암호);

    // 포인터 변수는 변수 앞에 '*'를 붙인다.
    int *암호_주소 = &암호;                                       // 변수의 주소를 꺼내려면 변수 앞에 '&'를 붙이면 된다.
    printf("암호 주소 : %d, 암호 : %d\n", 암호_주소, *암호_주소); // 포인터 변수 앞에 '*'를 붙이면 해당 주소의 값을 꺼낼 수 있다.

    // 배열과 포인터
    int arr[3] = {1, 2, 3};
    int *ptr = arr; // 배열 변수는 배열의 시작 주소를 가지고 있다. 포인터 변수에 배열의 첫 주소를 할당.
    printf("arr의 값 %d 과 arr[0]의 주소 %d는 같다.\n", arr, &arr[0]);

    // printf("배열 arr[0]의 값 : %d\n", 0, arr[0]);
    // printf("배열 arr[1]의 값 : %d\n", 1, arr[1]);
    // printf("배열 arr[2]의 값 : %d\n", 2, arr[2]);

    // arr은 배열의 시작 주소니까 i를 더하고 앞에 '*'를 붙이면 순차적으로 배열의 값을 알 수 있다.
    printf("배열 arr[0]의 값 : %d\n", 0, *(arr + 0));
    printf("배열 arr[1]의 값 : %d\n", 1, *(arr + 1));
    printf("배열 arr[2]의 값 : %d\n", 2, *(arr + 2));

    // printf("포인터 ptr[0]의 값 : %d\n", 0, ptr[0]);
    // printf("포인터 ptr[1]의 값 : %d\n", 1, ptr[1]);
    // printf("포인터 ptr[2]의 값 : %d\n", 2, ptr[2]);

    printf("포인터 ptr[0]의 값 : %d\n", 0, *(ptr + 0));
    printf("포인터 ptr[1]의 값 : %d\n", 1, *(ptr + 1));
    printf("포인터 ptr[2]의 값 : %d\n", 2, *(ptr + 2));
}