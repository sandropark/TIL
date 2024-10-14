#include <stdio.h>

typedef struct
{
    int accNum;
    double bal;
} BankAcc;

double sim_pow(double base, int year) // base=1.1, year=3
{
    int i;
    double r = 1.0;

    for (i = 0; i < year; i++) // 3번 반복
    {
        r = r * base;
    }
    return r;
}

void initAcc(BankAcc *acc, int x, double y)
{
    acc->accNum = x;
    acc->bal = y;
}

void xxx(BankAcc *acc, double *en)
{
    if (*en > 0 && *en < acc->bal)
    {
        acc->bal = acc->bal - *en;
    }
    else
    {
        acc->bal = acc->bal + *en;
    }
}

void yyy(BankAcc *acc)
{
    acc->bal = acc->bal * sim_pow((1 + 0.1), 3); // bal = 2100.0 * 1.331 = 2795.1
}

int main()
{

    BankAcc myAcc;
    initAcc(&myAcc, 9981, 2200.0); // accNum=9981, bal=2200.0
    double amount = 100.0;
    xxx(&myAcc, &amount); // bal = 2100.0
    yyy(&myAcc);
    printf("%d and %.2f", myAcc.accNum, myAcc.bal); // 9981 and 2795.10
    return 0;
}