#include <stdio.h>
#include <stdlib.h>

char n[30];

char *test() // 문자열을 반환
{
    printf("입력하세요 : ");
    gets(n);  // 배열에 값이 있어도 0부터 값을 넣기 때문에 덮어쓰기된다.
    return n; // n의 주소를 반환한다.
}

int main()
{
    char *test1;
    char *test2;
    char *test3;

    test1 = test(); // 홍길동
    test2 = test(); // 김철수
    test3 = test(); // 박영희

    // test1,2,3 모두 같은 주소를 공유한다.
    // gets에 n만 넘기기 때문에 마지막에 넣은 "박영희"로 덮어쓰기 된다.

    printf("%s\n", test1); // 박영희
    printf("%s\n", test2); // 박영희
    printf("%s", test3);   // 박영희
    return 0;
}