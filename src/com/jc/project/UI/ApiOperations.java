package com.jc.project.UI;

import com.jc.project.UI.Constrains.ExceptionStringConstants;
import com.jc.project.UI.Constrains.RandomWordsStringConst;
import com.jc.project.UI.Constrains.StringConstants;
import com.jc.project.UI.UIException.UIException;
import com.jc.project.UI.utils.HelperConsole;
import com.jc.project.be.Recording;
import com.jc.project.be.Word;
import com.jc.project.bll.Facade;
import com.jc.project.bll.IFacade;
import com.jc.project.bll.exception.BLLException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class ApiOperations {

    private final IFacade bllFacade;

    public ApiOperations() throws UIException {
        try {
            bllFacade = new Facade();
        } catch (BLLException e) {
           throw new UIException(ExceptionStringConstants.
                   COULDNT_CONNECT_TO_API);
        }
    }

    public void getRecordings() throws UIException {
        var words = getRandomWords();
        Map<Word, Recording> recordingsMap;
        try {
            System.out.println(RandomWordsStringConst.PULLING_RECORDINGS);
             recordingsMap = bllFacade.getRecordings(words);
        } catch (BLLException e) {
            throw new UIException();
        }
        if(recordingsMap!=null){
            showRecordingsToTheUser(recordingsMap);
        }
    }

    private void showRecordingsToTheUser(Map<Word, Recording> recordingsMap) {
        System.out.println(RandomWordsStringConst.REC);
        int i =0;
        for (var word: recordingsMap.keySet()
             ) {
            System.out.println(StringConstants.DIVIDER);
            System.out.println(RandomWordsStringConst.PAIR+ ++i);
            System.out.println(word);
            var recording = recordingsMap.get(word);
            if(recording==null)
                System.out.println(RandomWordsStringConst.NO_RECORDINGS);
            else
                System.out.println(recording);

        }
        System.out.println(StringConstants.DIVIDER);
    }


    private List<Word> getRandomWords(){
        //how many random words user wants to pull
        var noOfWordsToPull = HelperConsole.insertInt(RandomWordsStringConst.
                INSERT_NO_OF_WORDS_TO_PULL);

        //validate and if the input is wrong try again
        var isCorrect = bllFacade.inputNumberIsInTheRange(noOfWordsToPull);
        while (!isCorrect){
            noOfWordsToPull = HelperConsole.insertInt(RandomWordsStringConst.NO_OF_WORDS_IN_RANGE);
            isCorrect = bllFacade.inputNumberIsInTheRange(noOfWordsToPull);
        }

        List<Word> words =null;
        //get random words
        try {
            System.out.println(RandomWordsStringConst.PULLING_RANDOM_WORDS);
            words = bllFacade.getRandomWords(noOfWordsToPull);
        } catch (BLLException e) {
            System.out.println(ExceptionStringConstants.COULDNT_PULL_RANDOM_WORDS);
        }
        return words;
    }


    public void pullRandomWords() throws UIException {
        var words = getRandomWords();
        if(words!=null){
            System.out.println(RandomWordsStringConst.PULLED_WORDS);
            System.out.println(StringConstants.DIVIDER);
            for (var word: words
                 ) {
                System.out.println(word);
            }
        }

    }



}
