package problem.solution;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.apakhomov.leetenv.jupiter.annotations.Input;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Solution15Test {

    Solution15 solution;

    @BeforeEach
    void setUp() {
        solution = new Solution15Sort();
    }

    @ParameterizedTest
    @CsvSource({
            "'[-1,0,1,2,-1,-4]', '[[-1,-1,2],[-1,0,1]]'",
            "'[0,1,1]', '[]'",
            "'[0,0,0]', '[[0,0,0]]'"
    })
    void examples(@Input int[] input, @Input List<List<Integer>> expectedOutput) {
        assertEquals(expectedOutput, solution.threeSum(input));
    }
}
