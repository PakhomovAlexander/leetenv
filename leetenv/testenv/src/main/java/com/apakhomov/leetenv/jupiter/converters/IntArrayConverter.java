package com.apakhomov.leetenv.jupiter.converters;

import com.apakhomov.leetenv.core.parsers.IntArrayParser;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;

public class IntArrayConverter extends TypedArgumentConverter<String, int[]> {
    public IntArrayConverter() {
        super(String.class, int[].class);
    }

    @Override
    protected int[] convert(String s) throws ArgumentConversionException {
        return new IntArrayParser().parse(s);
    }
}
