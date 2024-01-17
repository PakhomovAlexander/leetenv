package problem.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apakhomov.leetenv.jupiter.annotations.Input;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class Solution55Test {

    Solution55 solution55;

    @BeforeEach
    void setUp() {
        solution55 = new Solution553();
    }

    @ParameterizedTest(name = "input: {0}, expected: {1}")
    @CsvSource({
            "'[2,3,1,1,4]', true",
            "'[3,2,1,0,4]', false",
            "'[2,0,0]',     true",
    })
    void testCases(@Input int[] input, boolean expected) {
        assertEquals(solution55.canJump(input), expected);
    }
}
