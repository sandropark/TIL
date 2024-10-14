#include <stdio.h>

int main(void)
{
    char *p = "KOREA";
    printf("%s\n", p);        // KOREA  // p는 문자열의 처음 주소를 가지고 있기 때문에 %s로 출력하면 처음부터 '\0'을 만날 때까지 문자를 출력한다.
    printf("%s\n", p + 3);    // EA     // 문자열 처음 주소에 3을 더한 다음 거기부터 출력한다.
    printf("%c\n", *p);       // K      // 문자열 시작 주소의 값을 출력하면 K가 된다.
    printf("%c\n", *(p + 3)); // E
    printf("%c\n", *p + 2);   // M      // K에 2를 더하고 문자로 출력하면 M이된다. K -> L -> M
}