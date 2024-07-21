package ru.cft;

public class StringParser {

    public StringType parse(String expression) {
        for (StringType type : StringType.values()) {
            if (expression.matches(type.getRegexp()))
                return type;
        }
        throw new IllegalArgumentException("Expression '" + expression + "' not parsed");
    }
}
