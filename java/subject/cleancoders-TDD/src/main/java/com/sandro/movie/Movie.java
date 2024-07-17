package com.sandro.movie;

public class Movie {
    private int rate;
    private int countOfRate;

    public Integer averageRating() {
        return countOfRate < 1
                ? 0
                : rate / countOfRate;
    }

    public void rate(int rate) {
        this.rate += rate;
        countOfRate++;
    }

}
