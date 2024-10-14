#include <stdio.h>
#define MAX_SIZE 10

int isWhat[MAX_SIZE];
int point = -1;

void into(int num)
{
    if (point >= 10)
        printf("Full");
    isWhat[++point] = num;
}

int isEmpty()
{
    return point == -1;

    // if (point == -1)
    //     return 1;
    // return 0;
}

int take()
{
    if (isEmpty() == 1)
        printf("Empty");
    return isWhat[point--];
}

int isFull()
{
    if (point == 10)
        return 1;
    return 0;
}

int main(int argc, char const *argv[])
{
    int e;
    into(5); // isWhat = [5]
    into(2); // isWhat = [5, 2]
    while (!isEmpty())
    {
        printf("%d", take()); // 2  isWhat = [5]
        into(4);              // isWhat = [5, 4]
        into(1);              // isWhat = [5, 4, 1]
        printf("%d", take()); // 1  isWhat = [5, 4]

        into(3);              // isWhat = [5, 4, 3]
        printf("%d", take()); // 3
        printf("%d", take()); // 4

        into(6);              // isWhat = [5, 6]
        printf("%d", take()); // 6
        printf("%d", take()); // 5
    }

    // 213465

    return 0;
}