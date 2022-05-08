package com.jc.project.UI;

import com.jc.project.UI.Constrains.ExceptionStringConstants;
import com.jc.project.UI.Constrains.StringConstants;
import com.jc.project.UI.UIException.UIException;
import com.jc.project.UI.options.Options;
import com.jc.project.UI.utils.HelperConsole;
import com.jc.project.UI.utils.OptionsHelper;

import java.util.Arrays;

public class Menu implements IMenu{
    private ApiOperations apiOperations;


    @Override
    public void StartUi() {
        if(apiOperations ==null){
            try {
                apiOperations = new ApiOperations();
            } catch (UIException e) {
                System.out.println(ExceptionStringConstants.COULDNT_CONNECT_TO_API);
            }
        }

        if(apiOperations != null){
            greetUser();
            showMenu();
            var  choice = getChoice();
            while(choice != Options.EXIT){
                switch (choice){
                    case PULL_RANDOM_WORDS -> pullRandomWords();
                    case PULL_RECORDINGS_INFO -> pullRecordings();
                }
                showMenu();
                choice = getChoice();
            }
            showApplicationHasFinished();
        }

    }

    private void pullRecordings(){
        try {
            apiOperations.getRecordings();
        } catch (UIException e) {
            System.out.println(e.getMessage());
            System.out.println(ExceptionStringConstants.COULDNT_PULL_RECORDINGS);
        }
    }


    private void pullRandomWords(){
        try {
            apiOperations.pullRandomWords();
        } catch (UIException e) {
            System.out.println(e.getMessage());
            System.out.println(ExceptionStringConstants.
                    COULDNT_PULL_RANDOM_WORDS);
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
