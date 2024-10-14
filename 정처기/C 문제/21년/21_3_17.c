#include <stdio.h>

struct jsu
{
    char name[12];
    int os, db, hab, hhab;
};

int main()
{
    // name, os, db만 값을 할당하고 나머지(hab, hhab)는 0으로 초기화 되어 있다.
    struct jsu st[3] = {{"데이터1", 95, 88},
                        {"데이터2", 84, 91},
                        {"데이터3", 86, 75}};
    struct jsu *p;

    p = &st[0]; // st[0]의 주소를 할당. 즉 st를 그냥 할당한 것과 같다. p == st

    // st의 두번째요소(데이터2).hab에 데이터2.os + 데이터3.db를 할당. = 159
    (p + 1)->hab = (p + 1)->os + (p + 2)->db;
    // 데이터2의 hhab에 데이터2.hab + 데이터1.os + 데이터1.db를 할당 = 342
    (p + 1)->hhab = (p + 1)->hab + p->os + p->db;

    printf("%d\n", (p + 1)->hab + (p + 1)->hhab); // 501
    // (p + 1)->hab = 84 + 75 = 159
    // (p + 1)->hhab = 159 + 95 + 88 = 342
}