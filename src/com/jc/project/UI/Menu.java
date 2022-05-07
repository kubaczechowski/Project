package com.jc.project.UI;

import com.jc.project.UI.Constrains.ExceptionStringConstants;
import com.jc.project.UI.Constrains.StringConstants;
import com.jc.project.UI.UIException.UIException;
import com.jc.project.UI.options.Options;
import com.jc.project.UI.utils.HelperConsole;
import com.jc.project.UI.utils.OptionsHelper;

import java.util.Arrays;

public class Menu implements IMenu{
    private RandomWordsOperation randomWordsOperation;


    @Override
    public void StartUi() {
        if(randomWordsOperation==null){
            try {
                randomWordsOperation = new RandomWordsOperation();
            } catch (UIException e) {
                System.out.println(ExceptionStringConstants.COULDNT_CONNECT_TO_API);
            }
        }

        if(randomWordsOperation!= null){
            greetUser();
            showMenu();
            var  choice = getChoice();
            while(choice != Options.EXIT){
                if(choice == Options.PULL_RANDOM_WORDS) {
                    try {
                        randomWordsOperation.pullRandomWords();
                    } catch (UIException e) {
                        //exception bubbles up through all layers and
                        //appropriate message is presented to the user
                        System.out.println(ExceptionStringConstants.
                                COULDNT_PULL_RANDOM_WORDS);
                    }
                }

                showMenu();
                choice = getChoice();

            }
            showApplicationHasFinished();
        }

    }

    private void showMenu() {
        System.out.println(StringConstants.DIVIDER);
        System.out.println(StringConstants.POSSIBLE_CHOICES);
        Arrays.stream(Options.values()).forEach(
                o -> System.out.println((o.getValue() +". " + o) // o.toString is redundant
                ));
        showDivider();
    }

    private void greetUser() {
        System.out.println(StringConstants.WELCOME_GREETINGS);
    }

    private void showDivider(){
        System.out.println(StringConstants.DIVIDER);
    }

    private Options getChoice(){
        var choice = HelperConsole.insertInt(StringConstants.YOUR_CHOICE);
        var option = OptionsHelper.getOptionFromInt(choice);
        while (option==null){
            System.out.println(StringConstants.PROVIDE_CHOICE_AGAIN);
            choice = HelperConsole.insertInt(StringConstants.YOUR_CHOICE);
            option = OptionsHelper.getOptionFromInt(choice);
        }
        return option;
    }

    private void showApplicationHasFinished() {

        System.out.println(StringConstants.APPLICATION_FINISHED);
    }
}
