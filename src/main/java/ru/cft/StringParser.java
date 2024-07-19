package ru.cft;

public class StringParser {

    public boolean isInteger(String expression){
        return expression.matches(StringType.INTEGER.regexp);
    }

    public boolean isDouble(String expression){
        return expression.matches(StringType.DOUBLE.regexp);
    }

    public StringType parse(String expression){
        for (StringType type : StringType.values()) {
            if (expression.matches(type.regexp))
                return type;
        }
        throw new IllegalArgumentException("Expression '" + expression + "' not parsed");
    }
}
