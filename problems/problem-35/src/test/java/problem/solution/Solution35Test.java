package problem.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apakhomov.leetenv.core.converters.IntArrayConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

class Solution35Test {

    Solution35 solution;

    @BeforeEach
    void setUp() {
        solution = new Solution35BinarySearch();
    }

    @ParameterizedTest(name = "input: {0}, target: {1}, expected: {2}")
    @CsvSource({
            "'[1,3,5,6]', 5, 2",
            "'[1,3,5,6]', 2, 1",
            "'[1,3,5,6]', 7, 4",
            "'[1,3,5,6]', 0, 0",
            "'[1,3]', 2, 1"
    })
    void testCases(@ConvertWith(IntArrayConverter.class) int[] input, int target, int expected) {
        assertEquals(expected, solution.searchInsert(input, target));
    }
}
