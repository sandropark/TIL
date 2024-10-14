#include <stdio.h>

int main(void)
{
    struct insa
    {
        char name[10];
        int age;
    }

    a[] = {"Kim", 28, "Lee", 38, "Park", 42, "Choi", 31};
    struct insa *p;

    p = a;                   // 배열의 시작 주소를 할당.
    p++;                     // 시작 주소에 1을 더하니까 두번째 요소부터 가르킨다. &p[0] -> &p[1]
    printf("%s\n", p->name); // Lee
    printf("%d\n", p->age);  // 38
}