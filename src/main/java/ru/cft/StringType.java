package ru.cft;

import lombok.Getter;

public enum StringType {
    INTEGER("-?[0-9]+"), DOUBLE("-?[0-9]+(.|,)[0-9]+"), STRING(".*");

    @Getter
    String regexp;

    StringType(String regexp) {
        this.regexp = regexp;
    }
}
