package com.apakhomov.leetenv.core.parsers;

import com.apakhomov.leetenv.core.parsers.exceptions.IntegerMatrixParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parses the integer matrix.
 *
 * <p>Input example: [[1,2,3],[4,5,6]], output {@code List[List[1,2,3],List[4,5,6]}.
 */
public class IntegerMatrixParser implements Parser<List<List<Integer>>> {
    private final String leftBound;
    private final String rightBound;
    private final String delim;

    private IntegerMatrixParser(String leftBound, String rightBound, String delim) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.delim = delim;
    }

    public IntegerMatrixParser() {
        this("[", "]", ",");
    }

    @Override
    public List<List<Integer>> parse(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        IntegerArrayListParser lineParser = new IntegerArrayListParser();

        var compactInput = input.replaceAll(" ", "");
        if (!compactInput.startsWith(leftBound)) {
            throw new IntegerMatrixParseException("Input string should start with: " + leftBound);
        }
        if (!compactInput.endsWith(rightBound)) {
            throw new IntegerMatrixParseException("Input string should end with: " + rightBound);
        }
        if (compactInput.equals(leftBound + rightBound)) {
            return new ArrayList<>();
        }

        var trimmedSplit = compactInput.substring(1, compactInput.length() - 1)
                .split(delim + "\\" + leftBound); // probably is not the best idea with \\

        return Arrays.stream(trimmedSplit)
                .map(s -> {
                    if (!s.startsWith(leftBound)) {
                        return leftBound + s;
                    } else {
                        return s;
                    }
                })
                .map(lineParser::parse)
                .collect(Collectors.toList());
    }
}
