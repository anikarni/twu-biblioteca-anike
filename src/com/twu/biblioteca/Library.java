package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by anikarni on 29/12/14.
 */
public class Library {
    private static String[] OPTIONS = {"List Books", "Quit"};

    private Book[] books;

    public Library(Book[] books){ this.books = books; }

    public Book[] getBooks(){ return this.books; }

    public void welcomeUser(){
        System.out.println("Welcome to Biblioteca!");
    }

    public void showAvailableBooks(){
        for (Book book: this.books){
            System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear());
        }
    }

    public void showMenu(){
        System.out.println("Menu:");
        for (String option: OPTIONS){
            System.out.println(option);
        }
    }

    public void selectOption(String option){
        if(option.equals("List Books")) {
            showAvailableBooks();
        }else if(option.equals("Quit")){
            quitLibrary();
        }else{
            invalidOption();
        }
    }

    public void invalidOption(){
        System.out.println("Select a valid option!");
    }

    public void quitLibrary(){
        System.out.println("Good bye!");
        System.exit(0);
    }
}