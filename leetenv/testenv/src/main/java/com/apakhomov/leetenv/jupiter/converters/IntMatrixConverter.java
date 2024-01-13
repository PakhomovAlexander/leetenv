package com.apakhomov.leetenv.jupiter.converters;

import com.apakhomov.leetenv.core.parsers.IntMatrixParser;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;

public class IntMatrixConverter extends TypedArgumentConverter<String, int[][]> {
    public IntMatrixConverter() {
        super(String.class, int[][].class);
    }

    @Override
    protected int[][] convert(String s) throws ArgumentConversionException {
        return new IntMatrixParser().parse(s);
    }
}
