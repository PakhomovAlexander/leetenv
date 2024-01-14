package problem.solution;

import static org.junit.jupiter.api.Assertions.*;

import com.apakhomov.leetenv.jupiter.annotations.IntArray;
import com.apakhomov.leetenv.jupiter.annotations.IntegerMatrix;
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
    void examples(@IntArray int[] input, @IntegerMatrix List<List<Integer>> expectedOutput) {
        assertEquals(expectedOutput, solution.threeSum(input));
    }
}
