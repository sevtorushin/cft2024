package ru.cft.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.cft.entity.StringType;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LineMapper {

    @NonNull
    private final StringParser parser;

    public Map<StringType, List<String>> map(@NonNull List<String> inputCollection) {
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
