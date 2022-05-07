package com.jc.project.dal.RandomWordsApi.converter;

import com.jc.project.be.Word;


public class ConvertStringToObject {
    private static final String WORD= "\"word\": \"";
    private static final String DEFINITION= "\"definition\": \"";
    private static final String PRONUNCIATION = "\"pronunciation\": \"";

    private static final String END = "\"";



    public Word convertStringToWord(String wordAsString) {
        var word = getStringInBetweenCustom(WORD, END, wordAsString);
        var definition = getStringInBetweenCustom(DEFINITION, END, wordAsString);
        var pron = getStringInBetweenCustom(PRONUNCIATION, END, wordAsString);
        return new Word(word, definition, pron);
    }

    public static String getStringInBetweenCustom(String start, String end, String str){
        return str.substring(str.lastIndexOf(start)+start.length())
                .split(end)[0];
    }

}
