package com.apakhomov.leetenv.core.parsers;

import com.apakhomov.leetenv.core.parsers.exceptions.IntegerArrayListParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Integer ArrayList parser. Parses input string into {@code ArrayList<Integer>}.
 *
 * <p> Input example: [1,2,4,5] -> ArrayList[1,2,4,5].
 * <p> Empty elements are supported: [ -1, , , 3, 4] -> ArrayList[-1,null,null,3,4].
 */
public class IntegerArrayListParser implements Parser<ArrayList<Integer>> {
    private final String leftBound;
    private final String rightBound;
    private final String delim;

    private IntegerArrayListParser(String leftBound, String rightBound, String delim) {
        assert leftBound.length() == 1;
        assert rightBound.length() == 1;
        assert Objects.equals(delim, ",") : "Only ',' delimiter is supported now";

        this.leftBound = leftBound;
        this.rightBound = rightBound;
        this.delim = delim;
    }

    public IntegerArrayListParser() {
        this("[", "]", ",");
    }

    @Override
    public ArrayList<Integer> parse(String input) {
        if (input == null || input.isBlank()) {
            return null;
        }

        StreamTokenizer st = streamTokenizer(input);

        return new InternalParser(leftBound, rightBound, delim, st).parseInternal();
    }

    private static StreamTokenizer streamTokenizer(String input) {
        StreamTokenizer st = new StreamTokenizer(new InputStreamReader(new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8))));
        st.wordChars('[', '[');
        st.wordChars(']', ']');
        return st;
    }

    private static class InternalParser {
        private final String leftBound;
        private final String rightBound;
        private final String delim;
        private final StreamTokenizer st;

        // State of the parser.
        private ArrayList<Integer> result = null;
        private boolean seenRightBound = false;
        private boolean prevSeenNum = false;
        private boolean prevSeenDelim = false;

        private InternalParser(String leftBound, String rightBound, String delim, StreamTokenizer st) {
            this.leftBound = leftBound;
            this.rightBound = rightBound;
            this.delim = delim;
            this.st = st;
        }

        private ArrayList<Integer> parseInternal() {
            try {
                while (st.nextToken() != StreamTokenizer.TT_EOF) {
                    switch (st.ttype) {
                        case StreamTokenizer.TT_EOL:
                            throw new IntegerArrayListParseException("EOL is not supported");
                        case StreamTokenizer.TT_NUMBER:
                            checkCanAddNumber();
                            addNumber();
                            continue;
                        case StreamTokenizer.TT_WORD:
                            if (st.sval.startsWith(leftBound)) {
                                result = new ArrayList<>();
                                if (st.sval.equals(leftBound)) {
                                    markListStarted();
                                    continue;
                                }
                                if (st.sval.equals(leftBound + rightBound)) {
                                    markListStarted();
                                    markListEnded();
                                    continue;
                                }
                                if (st.sval.endsWith(rightBound)) { // [-1]
                                    checkCanAddNumber();
                                    addStringAsNumberTrimBounds();
                                    markSeenNumber();
                                    markListEnded();
                                    continue;
                                }
                                // [22
                                addStringAsNumberTrimBounds();
                                markSeenNumber();
                            } else if (st.sval.endsWith(rightBound)) {
                                markListEnded();
                                if (st.sval.equals(rightBound)) {
                                    if (!prevSeenNum && prevSeenDelim) {
                                        result.add(null);
                                    }
                                    continue;
                                }
                                // 11]
                                addStringAsNumberTrimBounds();
                                markSeenNumber();
                            } else {
                                throw new IntegerArrayListParseException("Unknown symbol: " + st.sval);
                            }
                            continue;
                        case ',':
                            if (result == null) {
                                throw new IntegerArrayListParseException("Array should start with " + leftBound);
                            }
                            if (!prevSeenNum) { // [,,]
                                result.add(null);
                            }
                            markSeenDelim();
                            continue;
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

        private void addStringAsNumberTrimBounds() {
            String trimmed = st.sval;
            if (trimmed.startsWith(leftBound)) {
               trimmed = trimmed.substring(1);
            }
            if (trimmed.endsWith(rightBound)) {
                trimmed = trimmed.substring(0, trimmed.length() - 1);
            }

            result.add(Integer.parseInt(trimmed));
        }

        private void checkCanAddNumber() {
            if (seenRightBound) {
                throw new IntegerArrayListParseException("Got a number = " + st.nval + "  after " + rightBound);
            }
            if (result == null) {
                throw new IntegerArrayListParseException("Got number = " + st.sval + " before " + leftBound);
            }
        }

        private void markSeenDelim() {
            prevSeenNum = false;
            prevSeenDelim = true;
        }

        private void markSeenNumber() {
            prevSeenNum = true;
            prevSeenDelim = false;
        }

        private void markListEnded() {
            seenRightBound = true;
        }

        private void markListStarted() {
            prevSeenNum = false;
            prevSeenDelim = false;
        }

        private void addNumber() {
            result.add(Double.valueOf(st.nval).intValue());
            prevSeenNum = true;
        }

    }
}
