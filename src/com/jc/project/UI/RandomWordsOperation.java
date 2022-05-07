package com.jc.project.UI;

import com.jc.project.UI.Constrains.ExceptionStringConstants;
import com.jc.project.UI.Constrains.RandomWordsStringConst;
import com.jc.project.UI.Constrains.StringConstants;
import com.jc.project.UI.UIException.UIException;
import com.jc.project.UI.utils.HelperConsole;
import com.jc.project.be.Word;
import com.jc.project.bll.Facade;
import com.jc.project.bll.IFacade;
import com.jc.project.bll.exception.BLLException;

import java.util.List;

public class RandomWordsOperation {

    private final IFacade bllFacade;

    public RandomWordsOperation() throws UIException {
        try {
            bllFacade = new Facade();
        } catch (BLLException e) {
           throw new UIException(ExceptionStringConstants.
                   COULDNT_CONNECT_TO_API);
        }
    }

    public void pullRandomWords() throws UIException {
        //how many random words user wants to pull
        var noOfWordsToPull = HelperConsole.insertInt(RandomWordsStringConst.
                INSERT_NO_OF_WORDS_TO_PULL);


        //validate and if the input is wrong try again
        var isCorrect = bllFacade.inputNumberIsInTheRange(noOfWordsToPull);
        while (!isCorrect){
            noOfWordsToPull = HelperConsole.insertInt(RandomWordsStringConst.NO_OF_WORDS_IN_RANGE +
                    RandomWordsStringConst.INSERT_NO_OF_WORDS_TO_PULL);
            isCorrect = bllFacade.inputNumberIsInTheRange(noOfWordsToPull);
        }

        List<Word> words =null;
        //get that words
        try {
            words = bllFacade.getRandomWords(noOfWordsToPull);
        } catch (BLLException e) {
            System.out.println(ExceptionStringConstants.COULDNT_PULL_RANDOM_WORDS);
            //we may latter want to log the reason, but we don't want to
            //show our application structure to the user
            //we won't bubble up exception, we will just let the user know
            //that we couldn't pull words
        }

        //display that words in a nice way
        if(words!=null){

            System.out.println(RandomWordsStringConst.PULLED_WORDS);
            System.out.println(StringConstants.DIVIDER);
            for (var word: words
                 ) {
                System.out.println(word);
            }
            System.out.println(StringConstants.DIVIDER);
        }

    }
}
