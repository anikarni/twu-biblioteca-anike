package com.twu.biblioteca;

/**
 * Created by aarni on 1/8/15.
 */
public class Option {
    private String optionType;

    public Option(String optionType){
        this.optionType = optionType;
    }

    public Option(){}

    public void selectOption(String option, Library library){
        OptionFactory factory = new OptionFactory();
        factory.selectOption(new Option(option), library).execute();
    }

    public void execute(){
        invalidOption();
    }

    public void invalidOption(){
        System.out.println("Select a valid option!");
    }

    public boolean is(String optionType){
        return this.optionType.equals(optionType);
    }
}
