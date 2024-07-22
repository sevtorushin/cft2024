package ru.cft;

import lombok.NonNull;

public class StringParser {

    public StringType parse(@NonNull String expression) {
        for (StringType type : StringType.values()) {
            if (expression.matches(type.getRegexp()))
                return type;
        }
        throw new IllegalArgumentException("Expression '" + expression + "' not parsed");
    }
}
