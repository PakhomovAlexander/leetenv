package problem.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class Solution997Test {

    Solution997 solution;

    public static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(2, new Input(new Tup(1, 2)).toArr(), 2),
                Arguments.of(3, new Input(new Tup(1, 3), new Tup(2, 3)).toArr(), 3),
                Arguments.of(3, new Input(new Tup(1, 3), new Tup(2, 3), new Tup(3, 1)).toArr(), -1)
        );
    }

    record Tup(int i1, int i2) {
        int[] toArr() {
            var arr = new int[2];
            arr[0] = i1;
            arr[1] = i2;
            return arr;
        }
    }

    record Input(Tup... tups) {
        int[][] toArr() {
            int[][] res = new int[tups.length][2];
            for (int i = 0; i < res.length; i++) {
                res[i] = tups[i].toArr();
            }

            return res;
        }
    }

    @BeforeEach
    void setUp() {
        solution = new Solution997Impl();
    }

    @ParameterizedTest(name = "input: {0}, target: {1}, expected: {2}")
    @MethodSource("inputs")
    void testCases(int n, int[][] input, int expected) {
        assertEquals(expected, solution.findJudge(n, input));
    }
}
