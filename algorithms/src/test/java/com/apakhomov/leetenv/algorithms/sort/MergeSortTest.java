package com.apakhomov.leetenv.algorithms.sort;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class MergeSortTest {
    Sort sort = new MergeSort();

    public static Stream<Arguments> input() {
        return Stream.of(
                Arguments.of(new int[]{1, 3, 5, 4, 6, 7, 2, 8}, new int[]{1, 2, 3, 4, 5, 6, 7, 8}),
                Arguments.of(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{4, 3, 2, 1}, new int[]{1, 2, 3, 4}),
                Arguments.of(new int[]{1}, new int[]{1}),
                Arguments.of(new int[]{2, 1}, new int[]{1, 2}),
                Arguments.of(new int[]{2, 3, 1}, new int[]{1, 2, 3}),
                Arguments.of(new int[]{}, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("input")
    void sort(int[] input, int[] expected) {
        assertArrayEquals(expected, sort.sort(input));
    }
}