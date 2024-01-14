package com.apakhomov.leetenv.jupiter.converters;

import com.apakhomov.leetenv.core.parsers.IntegerMatrixParser;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class IntegerMatrixConverter implements ArgumentConverter {
    @Override
    public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
        return new IntegerMatrixParser().parse((String) o);
    }
}
