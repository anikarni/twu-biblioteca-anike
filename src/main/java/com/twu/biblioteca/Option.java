package com.twu.biblioteca;

/**
 * Created by aarni on 1/8/15.
 */
public class Option {
    public Option(){}

    public void selectOption(String option, Library library){
        OptionFactory factory = new OptionFactory();
        factory.selectOption(option, library);
    }

    public void execute(){
        invalidOption();
    }

    public void quitLibrary(){
        System.out.println("Goodbye!");
    }

    public void invalidOption(){
        System.out.println("Select a valid option!");
    }
}
