package com.apakhomov.leetenv.jupiter.converters;

import com.apakhomov.leetenv.core.parsers.IntArrayParser;
import com.apakhomov.leetenv.core.parsers.IntMatrixParser;
import com.apakhomov.leetenv.core.parsers.IntegerArrayListParser;
import com.apakhomov.leetenv.core.parsers.IntegerMatrixParser;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;

public class UntypedArgumentConverter implements ArgumentConverter {
    @Override
    public Object convert(Object o, ParameterContext parameterContext) throws ArgumentConversionException {
        Type type = parameterContext.getParameter().getParameterizedType();

        // holy cow

        if (type.getTypeName().equals(int[].class.getTypeName())) {
            // int[]
            return new IntArrayParser().parse((String) o);
        }

        if (type.getTypeName().equals(int[][].class.getTypeName())) {
            // int[][]
            return new IntMatrixParser().parse((String) o);
        }

        // SomeType<T1,...>
        if (type instanceof ParameterizedType parameterizedType) {
            if (!parameterizedType.getRawType().equals(List.class)) {
               return null;
            }

            // List<T1,...>
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (actualTypeArguments.length == 1) {
                // List<T>
                Type innerTypeArgument = actualTypeArguments[0];
                if (innerTypeArgument instanceof ParameterizedType innerParametrizedType) {
                    if (!innerParametrizedType.getRawType().equals(List.class)) {
                       return null;
                    }
                    // List<List<T1, ...>
                    if (innerParametrizedType.getActualTypeArguments().length == 1) {
                        // List<List<T>>
                        Type actualTypeArgument = innerParametrizedType.getActualTypeArguments()[0];
                        if (actualTypeArgument.getTypeName().equals(Integer.class.getTypeName())) {
                            // List<List<Integer>>
                            return new IntegerMatrixParser().parse((String) o);
                        }
                    }
                }
            }
        }

        return null;
    }
}
