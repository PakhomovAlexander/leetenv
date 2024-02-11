package com.apakhomov.leetenv.algorithms.sort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import javax.print.DocFlavor;

import static org.junit.jupiter.api.Assertions.*;

class HeapSortTest {
    Sort sort;

    @BeforeEach
    void setUp() {
        sort = new HeapSort();
    }

    @ParameterizedTest
    @MethodSource(SortTestInputs.INPUT_METHOD_PATH)
    void test(int[] given, int[] expected) {
        assertArrayEquals(expected, sort.sort(given));
    }
}