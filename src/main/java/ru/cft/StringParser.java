package ru.cft;

public class StringParser {

    public boolean isInteger(String expression){
        return expression.matches(StringType.INTEGER.getRegexp());
    }

    public boolean isDouble(String expression){
        return expression.matches(StringType.FLOAT.getRegexp());
    }

    public StringType parse(String expression){
        for (StringType type : StringType.values()) {
            if (expression.matches(type.getRegexp()))
                return type;
        }
        throw new IllegalArgumentException("Expression '" + expression + "' not parsed");
    }
}
