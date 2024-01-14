package com.apakhomov.leetenv.core.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@TestInstance(Lifecycle.PER_CLASS)
class IntegerMatrixParserTest {
    IntegerMatrixParser parser;

    @BeforeEach
    void setUp() {
        parser = new IntegerMatrixParser();
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,2,3],[4,5,6],[-7,-7,-7]]'",
            "'[ [1,2,3],[4,5,6],[-7,-7,-7]   ]'",
            "' [  [ 1 , 2,3] ,   [4,5,6],   [-7,-7, -7] ]  '",
    })
    void parseMatrix(String input) {
        var expected = matrix(
                row(1, 2, 3),
                row(4, 5, 6),
                row(-7, -7, -7)
        );

        assertEquals(expected, parser.parse(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void empty(String input) {
        assertNull(parser.parse(input));
    }

    @ParameterizedTest
    @MethodSource("cornerCases")
    void cornerCases(String input, List<List<Integer>> expected) {
        assertEquals(expected, parser.parse(input));
    }

    static Stream<Arguments> cornerCases() {
        return Stream.of(
                Arguments.of("[[]]", matrix(row())),
                Arguments.of("[[],[]]", matrix(row(), row())),
                Arguments.of("[[1,2],[]]", matrix(row(1, 2), row())),
                Arguments.of("[[],[1,2 ]]", matrix(row(), row(1, 2))),
                Arguments.of("[]", matrix())
//               Arguments.of("[,[,],]", matrix(null, row(null, null), null)) todo: , at the end is not supported yet.
        );
    }

    private static ArrayList<ArrayList<Integer>> matrix(ArrayList<Integer>... rows) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>(rows.length);
        res.addAll(Arrays.asList(rows));
        return res;
    }

    private static ArrayList<Integer> row(Integer... nums) {
        ArrayList<Integer> res = new ArrayList<>(nums.length);
        res.addAll(Arrays.asList(nums));
        return res;
    }
}
