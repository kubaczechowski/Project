package com.jc.project.dal.utlis;

public class Converter {
    protected static final String END = "\"";
    protected static String getStringInBetweenCustom(String start, String end, String str){
        return str.substring(str.lastIndexOf(start)+start.length())
                .split(end)[0];
    }
}
