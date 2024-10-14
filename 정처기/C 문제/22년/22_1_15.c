#include <stdio.h>

int main()
{
    int number = 1234;
    int div = 10;
    int result = 0;

    while (number > 0)
    {

        result = result * div;          // number의 자리수 증가
        result = result + number % div; // 1의 자리의 값을 result에 합산
        number = number / div;          // number의 1의 자리를 날리기
    }

    printf("%d", result); // 4321
    return 0;
}

/*
반복 1
result = result * div; -> result는 0이기 때문에 0이다.
result = result + number % div; -> 0 + 1234 % 10이다. 1234 % 10은 결국 1의 자리의 수만 반환한다. 4다. 0 + 4 = 4로 result=4다.
number = number / div; -> 1234 / 10으로 123이 된다.

반복 2
result = result * div; -> result는 4로 4 * 10으로 40이 된다.
result = result + number % div; -> 123 % 10은 3이고 40 + 3은 43이다.
number = number / div; -> 123 / 10은 12다.
*/