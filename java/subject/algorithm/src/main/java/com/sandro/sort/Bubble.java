package com.sandro.sort;

import lombok.AccessLevel;
import lombok.Getter;

import java.util.Arrays;

public class Bubble {

    private int[] arr;
    @Getter(value = AccessLevel.PROTECTED)
    int loopTime;

    public Bubble(int... arr) {
        this.arr = arr;
    }

    public void sort() {
        loopTime = 0;
        int length = arr.length;
        for (int i = 0; i < length; i++) {
            boolean notSwapped = true;
            for (int j = 0; j < length - 1 - i; j++) {
                int temp = arr[j];
                int next = arr[j + 1];

                if (temp > next) {
                    arr[j + 1] = temp;
                    arr[j] = next;
                    notSwapped = false;
                }
            }
            loopTime++;
            if (notSwapped)
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bubble bubble = (Bubble) o;
        return Arrays.equals(arr, bubble.arr);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }
}
