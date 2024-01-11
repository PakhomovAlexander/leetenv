package com.apakhomov.leetenv.core.parsers;

public class IntArrayParser implements Parser<int[]> {
    @Override
    public int[] parse(String input) {
        var trimmedStr = input.trim();
        // get rid of []
        var cutStr = trimmedStr.substring(1, trimmedStr.length() - 1);

        String[] split = cutStr.split(",");
        int[] result = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            result[i] = Integer.parseInt(split[i]);
        }

        return result;
    }
}
