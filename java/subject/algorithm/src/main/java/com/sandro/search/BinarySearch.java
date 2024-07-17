package com.sandro.search;

import java.util.Arrays;

public class BinarySearch {

    private final int[] arr;

    public BinarySearch(int... arr) {
        this.arr = Arrays.stream(arr).sorted().toArray();
    }

    public int[] search(int... targetList) {
        int[] result = new int[targetList.length];

        for (int i = 0; i < targetList.length; i++) {

            int target = targetList[i];
            int startIndex = 0;
            int lastIndex = arr.length - 1;

            while (startIndex <= lastIndex) {
                int centerIndex = (startIndex + lastIndex) / 2;
                int temp = arr[centerIndex];

                if (temp == target) {
                    result[i] = 1;
                    break;
                }

                if (temp > target)
                    lastIndex = centerIndex - 1;

                if (temp < target)
                    startIndex = centerIndex + 1;
            }
        }
        return result;
    }

}
