package com.apakhomov.leetenv.core.parsers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.apakhomov.leetenv.core.parsers.exceptions.IntegerArrayListParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class IntegerArrayListParserTest {

    IntegerArrayListParser parser;

    static Stream<Arguments> cornerCases() {
        return Stream.of(
                Arguments.of("[1]", arrayList(1)),
                Arguments.of("[ 1 ]", arrayList(1)),
                Arguments.of("[-1]", arrayList(-1)),
                Arguments.of("[-1 ]", arrayList(-1)),
                Arguments.of(" [9999]  ", arrayList(9999)),
                Arguments.of("[1,2,,,4]", arrayList(1, 2, null, null, 4)),
                Arguments.of("[,,]", arrayList(null, null, null))
        );
    }

    @BeforeEach
    void setUp() {
        parser = new IntegerArrayListParser();
    }

    @ParameterizedTest
    @CsvSource({
            "'[1,2,3,99,0,-3]'",
            "'[  1,2,3,99,0,-3]'",
            "'[1,2,3,99,0,-3  ]'",
            "' [  1,  2, 3,  99, 0, -3] '",
            "'[    1,2,3,99,0,-3        ]'",
    })
    void valid(String input) {
        ArrayList<Integer> expected = arrayList(1, 2, 3, 99, 0, -3);

        assertEquals(expected, parser.parse(input));
    }

    @ParameterizedTest
    @MethodSource("cornerCases")
    void cornerCases(String input, ArrayList<Integer> expected) {
        assertEquals(expected, parser.parse(input));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void expectNull(String input) {
        assertNull(parser.parse(input));
    }

    @ParameterizedTest
    @CsvSource({
            "[]",
            "  [  ]  ",
            " []",
            "[] "
    })
    void expectEmptyList(String input) {
        assertTrue(parser.parse(input).isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1234",
            " 1 ",
            " -1",
            "[1,2,3 ",
            "[1,2,3, ",
            "1,2,3]",
            "[[1]]",
            "[[]]",
            "not a list",
            "[not, an, integer, list]",
            "[1,2,word, 3]",
            "[1,2,3,4] 4",
            "[1,2] should not be here"
    })
    void expectThrows(String input) {
        assertThrows(IntegerArrayListParseException.class, () -> parser.parse(input));
    }

    private static ArrayList<Integer> arrayList(Integer... values) {
        ArrayList<Integer> res = new ArrayList<>(values.length);
        res.addAll(Arrays.asList(values));

        return res;
    }
}
