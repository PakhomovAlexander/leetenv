package com.apakhomov.leetenv.algorithms.sort;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import static com.apakhomov.leetenv.algorithms.sort.SortTestInputs.INPUT_METHOD_PATH;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


class MergeSortTest {
    Sort sort = new MergeSort();

    @ParameterizedTest
    @MethodSource(INPUT_METHOD_PATH)
    void sort(int[] given, int[] expected) {
        assertArrayEquals(expected, sort.sort(given));
    }
}