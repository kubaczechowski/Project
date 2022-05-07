package com.jc.project.bll.validations;

public class Validator {
    private static final int MIN_NO = 5;
    private static final int MAX_NO = 20;

    public boolean inputNumberIsInTheRange(int value) {
        return value>= MIN_NO && value <=MAX_NO;
    }
}
