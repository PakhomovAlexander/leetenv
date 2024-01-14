package com.apakhomov.leetenv.core.parsers;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class IntegerMatrixParser implements Parser<List<List<Integer>>> {
    private static final String DELIM = ",";

    @Override
    public List<List<Integer>> parse(String input) {
        var tokenizer = new StringTokenizer(input.replaceAll(" ", ""));
        List<List<Integer>> matrix = new ArrayList<>();
        // todo: code is duplicated with IntMatrixParser
        int currLineInd = 0;

        while (tokenizer.hasMoreTokens()) {
            var token = tokenizer.nextToken(DELIM);

            if (token.startsWith("[[")) {
                matrix.add(new ArrayList<>());
                matrix.get(currLineInd).add(Integer.parseInt(token.substring(2)));
            } else if (token.startsWith("[")) {
                matrix.add(new ArrayList<>());
                matrix.get(currLineInd).add(Integer.parseInt(token.substring(1)));
            } else if (token.endsWith("]]")) {
                matrix.get(currLineInd).add(Integer.parseInt(token.substring(0, token.length() - 2)));
            } else if (token.endsWith("]")) {
                matrix.get(currLineInd).add(Integer.parseInt(token.substring(0, token.length() - 1)));
                currLineInd++;
            }
        }
        return matrix;
    }
}
