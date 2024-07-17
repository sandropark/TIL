package com.sandro.money;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MoneyTest {

    private final Money fiveBucks = Money.dollar(5);
    private final Money fiveFrancs = Money.franc(5);
    private final Money tenFrancs = Money.franc(10);
    private final Bank bank = new Bank();

    @BeforeEach
    void setUp() {
        bank.addRate("CHF", "USD", 2);
    }

    @Test
    void multiplication() throws Exception {
        assertEquals(Money.dollar(10), fiveBucks.times(2));
        assertEquals(Money.dollar(15), fiveBucks.times(3));
    }

    @Test
    void equality() throws Exception {
        assertEquals(fiveBucks, Money.dollar(5));
        assertNotEquals(fiveBucks, Money.dollar(6));
        assertNotEquals(fiveFrancs, fiveBucks);
    }

    @Test
    void francMultiplication() throws Exception {
        assertEquals(Money.franc(10), fiveFrancs.times(2));
        assertEquals(Money.franc(15), fiveFrancs.times(3));
    }

    @Test
    void currency() throws Exception {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }

    @Test
    void simpleAddition() throws Exception {
        Expression sum = fiveBucks.plus(fiveBucks);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(10), reduced);
    }

    @Test
    void plusReturnsSum() throws Exception {
        Expression result = fiveBucks.plus(fiveBucks);
        Sum sum = (Sum) result;
        assertEquals(fiveBucks, sum.augend);
        assertEquals(fiveBucks, sum.addend);
    }

    @Test
    void reduceSum() throws Exception {
        Expression sum = new Sum(Money.dollar(3), Money.dollar(4));
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(7), result);
    }

    @DisplayName("달러를 달러로 변환하는 경우 그대로다.")
    @Test
    void reduceMoney() throws Exception {
        Money result = bank.reduce(Money.dollar(1), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @DisplayName("CHF 2 : USD 1 - 2프랑은 1달러이다.")
    @Test
    void reduceMoneyDifferentCurrency() throws Exception {
        Money result = bank.reduce(Money.franc(2), "USD");
        assertEquals(Money.dollar(1), result);
    }

    @DisplayName("다른 두 화폐를 더하면 환율이 적용되어 계산된다.")
    @Test
    void mixedAddition() throws Exception {
        Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
        assertEquals(Money.dollar(10), result);
    }

    @Test
    void sumPlusMoney() throws Exception {
        Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(15), result);
    }

    @DisplayName("두 수를 더한 다음 곱한다.")
    @Test
    void sumTimes() throws Exception {
        Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
        Money result = bank.reduce(sum, "USD");
        assertEquals(Money.dollar(20), result);
    }

}
