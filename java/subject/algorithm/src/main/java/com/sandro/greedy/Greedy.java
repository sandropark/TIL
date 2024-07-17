package com.sandro.greedy;

public class Greedy {

    private static final int[] coins = new int[]{1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000};

    public int getResult(int target) {
        int result = 0;

        for (int i = coins.length - 1; i >= 0; i--) {
            int coin = coins[i];

            if (target >= coin) {
                result += target / coin;
                target %= coin;
            }
        }

        return result;
    }
}
