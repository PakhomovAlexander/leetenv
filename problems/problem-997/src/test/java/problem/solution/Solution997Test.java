package problem.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apakhomov.leetenv.jupiter.annotations.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Solution997Test {

    Solution997 solution;

    @BeforeEach
    void setUp() {
        solution = new Solution997Impl();
    }

    @ParameterizedTest(name = "input: {0}, target: {1}, expected: {2}")
    @CsvSource({
            "2, '[[1,2]]',              2",
            "3, '[[1,3],[2,3]]',        3",
            "3, '[[1,3],[2,3],[3,1]]', -1",
    })
    void testCases(int n, @Input int[][] input, int expected) {
        assertEquals(expected, solution.findJudge(n, input));
    }
}
