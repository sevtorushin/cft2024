package ru.cft;

import lombok.Getter;

public enum StringType {
    INTEGER("^-?[0-9]\\d*$"), FLOAT("^[-+]?[0-9]*[.,]?[0-9]+(?:[eE][-+]?[0-9]+)?$"), STRING(".*");

    @Getter
    private final String regexp;

    StringType(String regexp) {
        this.regexp = regexp;
    }
}
