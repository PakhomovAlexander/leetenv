package com.apakhomov.leetenv.core.parsers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class IntArrayParser implements Parser<int[]> {
    private final String leftBound;
    private final String rightBound;
    private final String delim;
    private final boolean trim;

    private IntArrayParser(String leftBound, String rightBound, String delim, boolean trim) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.delim = delim;
        this.trim = trim;
    }

   public IntArrayParser() {
       this("[", "]", ",", true);
   }

    @Override
    public int[] parse(String input) {
        ArrayList<Integer> arrayList = new IntegerArrayListParser().parse(input);
        int[] array = new int[arrayList.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = arrayList.get(i);
        }

        return array;
    }
}
