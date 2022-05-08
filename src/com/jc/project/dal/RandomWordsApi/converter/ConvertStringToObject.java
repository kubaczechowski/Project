package com.jc.project.dal.RandomWordsApi.converter;

import com.jc.project.be.Word;
import com.jc.project.dal.utlis.Converter;


public class ConvertStringToObject extends Converter {
    private static final String WORD= "\"word\": \"";
    private static final String DEFINITION= "\"definition\": \"";
    private static final String PRONUNCIATION = "\"pronunciation\": \"";





    public Word convertStringToWord(String wordAsString) {
        var word = getStringInBetweenCustom(WORD, END, wordAsString);
        var definition = getStringInBetweenCustom(DEFINITION, END, wordAsString);
        var pron = getStringInBetweenCustom(PRONUNCIATION, END, wordAsString);
        return new Word(word, definition, pron);
    }



}
