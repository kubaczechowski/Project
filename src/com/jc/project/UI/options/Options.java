package com.jc.project.UI.options;

public enum Options {
    EXIT(0),
    PULL_RANDOM_WORDS(1),
    PULL_RECORDINGS_INFO(2);

    private final int value;

    Options(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
