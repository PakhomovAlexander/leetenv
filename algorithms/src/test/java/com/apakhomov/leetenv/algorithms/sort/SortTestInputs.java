package com.apakhomov.leetenv.algorithms.sort;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class SortTestInputs {
    public final static String INPUT_METHOD_PATH = "com.apakhomov.leetenv.algorithms.sort.SortTestInputs#intArrayInput";

    public static Stream<Arguments> intArrayInput() {
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
}
