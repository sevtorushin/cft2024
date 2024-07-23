package ru.cft.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;
import ru.cft.entity.StringType;

@Service
public class StringParser {

    public StringType parse(@NonNull String expression) {
        for (StringType type : StringType.values()) {
            if (expression.matches(type.getRegexp()))
                return type;
        }
        throw new IllegalArgumentException("Expression '" + expression + "' not parsed");
    }
}
