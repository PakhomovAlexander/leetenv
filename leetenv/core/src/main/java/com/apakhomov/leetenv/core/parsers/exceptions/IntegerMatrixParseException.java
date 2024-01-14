package com.apakhomov.leetenv.core.parsers.exceptions;

public class IntegerMatrixParseException extends RuntimeException {
    public IntegerMatrixParseException(String msg) {
        super(msg);
    }

    public IntegerMatrixParseException(Exception e) {
        super(e);
    }
}
