package com.twu.biblioteca;

/**
 * Created by aarni on 1/14/15.
 */
public class QuitOption extends Option {
    private Library library;

    public QuitOption(Library library){
        this.library = library;
    }

    public void execute(){ quitLibrary(); }

    public void quitLibrary(){
        System.out.println("Goodbye!");
    }
}
