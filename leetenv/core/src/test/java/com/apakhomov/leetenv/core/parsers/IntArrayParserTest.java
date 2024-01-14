package com.apakhomov.leetenv.core.parsers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class IntArrayParserTest {

    @ParameterizedTest
    @CsvSource({
            "'[1,2,3,99,0,-3]'",
            "'[  1,2,3,99,0,-3]'",
            "'[1,2,3,99,0,-3  ]'",
            "' [  1,  2, 3,  99, 0, -3] '",
            "'[    1,2,3,99,0,-3        ]'",
    })
    void valid(String input) {
        var parser = new IntArrayParser();

        int[] expected =  new int[]{
                1,2,3,99,0,-3
        };

        assertArrayEquals(expected, parser.parse(input));
    }

}
