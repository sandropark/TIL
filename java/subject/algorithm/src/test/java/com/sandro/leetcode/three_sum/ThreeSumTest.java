package com.sandro.leetcode.three_sum;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ThreeSumTest {
    @Test
    void threeSumTest() throws Exception {
        assertThat(threeSum(0, 0, 0)).contains(List.of(0, 0, 0));
        assertThat(threeSum(0, 1, 1)).isEmpty();
        assertThat(threeSum(0, 1, -1).get(0)).contains(-1, 0, 1);

        assertThat(threeSum(0, 1, -1, 1)).hasSize(1);
        threeSum(0, 1, -1, 1).forEach(list -> assertThat(list).contains(-1, 0, 1));
    }

    private List<List<Integer>> threeSum(int... nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (sum == 0 && notContains(result, List.of(nums[i], nums[j], nums[k]))) {
                        result.add(List.of(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        return result;
    }

    private boolean notContains(List<List<Integer>> result, List<Integer> list) {
        for (List<Integer> integers : result) {
            if (list.containsAll(integers))
                return false;
        }
        return true;
    }
}
