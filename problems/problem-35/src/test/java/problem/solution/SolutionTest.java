package problem.solution;

import static com.apakhomov.leetenv.core.converters.InputConverters.intArr;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

class SolutionTest {

    Solution solution;

    @BeforeEach
    void setUp() {
        solution = new SolutionBinarySearch();
    }

    @ParameterizedTest(name = "input: {0}, target: {1}, expected: {2}")
    @CsvSource({
            "'[1,3,5,6]', 5, 2",
            "'[1,3,5,6]', 2, 1",
            "'[1,3,5,6]', 7, 4",
            "'[1,3,5,6]', 0, 0",
            "'[1,3]', 2, 1"
    })
    void testCases(@ConvertWith(IntArrConverter.class) int[] input, int target, int expected) {
       assertEquals(expected, solution.searchInsert(input, target));
    }

    public static class IntArrConverter implements ArgumentConverter {

        @Override
        public Object convert(Object source, ParameterContext parameterContext) throws ArgumentConversionException {
            if (!(source instanceof String)) {
                throw new IllegalArgumentException(
                        "The argument should be a string: " + source);
            }

            return intArr((String) source);
        }
    }

}
