package com.apakhomov.leetenv.core.parsers.exceptions;

public class IntegerArrayListParseException extends RuntimeException {
    public IntegerArrayListParseException(String msg) {
        super(msg);
    }

    public IntegerArrayListParseException(Exception e) {
        super(e);
    }
}
