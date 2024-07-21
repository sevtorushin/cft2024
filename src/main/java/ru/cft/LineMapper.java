package ru.cft;

import java.util.*;

public class LineMapper {

    private final StringParser parser;

    public LineMapper(StringParser parser) {
        this.parser = parser;
    }

    public Map<StringType, List<String>> map(List<String> inputCollection) {
        Map<StringType, List<String>> result = fill();
        StringType type;
        List<String> target;
        for (String s : inputCollection) {
            type = parser.parse(s);
            target = result.get(type);
            target.add(s);
        }
        return result;
    }

    private Map<StringType, List<String>> fill() {
        Map<StringType, List<String>> result = new HashMap<>();
        Arrays.stream(StringType.values()).forEach(stringType -> result.put(stringType, new ArrayList<>()));
        return result;
    }
}
