package com.apakhomov.leetenv.core.parsers;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class IntMatrixParser implements Parser<int[][]> {
    private static final String DELIM = ",";

    @Override
    public int[][] parse(String input) {
        var tokenizer = new StringTokenizer(input.replaceAll(" ", ""));
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
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
            } else if (token.endsWith("]")){
                matrix.get(currLineInd).add(Integer.parseInt(token.substring(0, token.length() - 1)));
                currLineInd++;
            }
        }

        return toPrimitives(matrix);
    }

    private int[][] toPrimitives(ArrayList<ArrayList<Integer>> matrix) {
        int[][] result = new int[matrix.size()][];
        for (int i = 0; i < result.length; i++) {
            result[i] = new int[matrix.get(0).size()];
            for (int j = 0; j < matrix.get(0).size(); j++) {
                result[i][j] = matrix.get(i).get(j);
            }
        }

        return result;
    }
}
