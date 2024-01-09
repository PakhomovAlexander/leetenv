package com.apakhomov.leetenv.core.converters;

public class InputConverters {
    /**
     * Converts a string input like "[1,2,3]" into array of integers int[].
     *
     * @param stringInput input string
     * @return array of integers
     */
    public static int[] intArr(String stringInput) {
        var trimmedStr = stringInput.trim();
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
