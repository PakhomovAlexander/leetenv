package com.apakhomov.leetenv.core.parsers;

import com.apakhomov.leetenv.core.parsers.exceptions.IntegerArrayListParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class IntegerArrayListParser implements Parser<ArrayList<Integer>> {
    private final String leftBound;
    private final String rightBound;
    private final String delim;
    private final boolean trim;

    private IntegerArrayListParser(String leftBound, String rightBound, String delim, boolean trim) {
        assert leftBound.length() == 1;
        assert rightBound.length() == 1;

        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.delim = delim;
        this.trim = trim;
    }

    public IntegerArrayListParser() {
        this("[", "]", ",", true);
    }

    @Override
    public ArrayList<Integer> parse(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        StreamTokenizer st = streamTokenizer(input);

        ArrayList<Integer> result = null;
        boolean seenRightBound = false;
        boolean prevSeenNum = false;
        boolean prevSeenDelim = false;

        try {
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                switch (st.ttype) {
                    case StreamTokenizer.TT_EOL:
                        throw new RuntimeException();
                    case StreamTokenizer.TT_NUMBER:
                        if (seenRightBound) {
                            throw new IntegerArrayListParseException("Got a number = " + st.nval + "  after " + rightBound);
                        }
                        if (result == null) {
                            throw new IntegerArrayListParseException("Got number = " + st.sval + "before " + leftBound);
                        }
                        result.add(Double.valueOf(st.nval).intValue());
                        prevSeenNum = true;
                        continue;
                    case StreamTokenizer.TT_WORD:
                        if (st.sval.startsWith(leftBound)) {
                            result = new ArrayList<>();
                            if (st.sval.equals(leftBound)) {
                                prevSeenNum = false;
                                prevSeenDelim = false;
                                continue;
                            }
                            if (st.sval.equals(leftBound + rightBound)) {
                                prevSeenNum = false;
                                seenRightBound = true;
                                prevSeenDelim = false;
                                continue;
                            }
                            if (st.sval.endsWith(rightBound)) { // [-1]
                                result.add(Integer.parseInt(st.sval.substring(1, st.sval.length() - 1)));
                                prevSeenNum = true;
                                seenRightBound = true;
                                prevSeenDelim = false;
                                continue;
                            }
                            // [22
                            result.add(Integer.parseInt(st.sval.substring(1)));
                            prevSeenNum = true;
                            prevSeenDelim = false;
                        } else if (st.sval.endsWith(rightBound)) {
                            seenRightBound = true;
                            if (st.sval.equals(rightBound)) {
                                if (!prevSeenNum && prevSeenDelim) {
                                   result.add(null);
                                }
                                prevSeenNum = false;
                                prevSeenDelim = false;
                                continue;
                            }
                            // 11]
                            result.add(Integer.parseInt(st.sval.substring(0, st.sval.length() - 1)));
                            prevSeenNum = true;
                        } else {
                            throw new IntegerArrayListParseException("Unknown symbol: " + st.sval);
                        }
                        break;
                    case ',':
                        if (!prevSeenNum) { // [,,]
                            result.add(null);
                        }
                        prevSeenNum = false;
                        prevSeenDelim = true;
                        break;
                    default:
                        throw new IntegerArrayListParseException("Unknown token type: " + st.ttype);
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new IntegerArrayListParseException(e);
        }

        if (!seenRightBound) {
            throw new IntegerArrayListParseException("Did not find " + rightBound + " at the end of list");
        }

        return result;
    }

    private static StreamTokenizer streamTokenizer(String input) {
        StreamTokenizer st = new StreamTokenizer(new InputStreamReader(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))));
        st.wordChars('[', '[');
        st.wordChars(']', ']');
        return st;
    }
}
