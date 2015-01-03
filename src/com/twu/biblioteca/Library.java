package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anikarni on 29/12/14.
 */
public class Library {
    private static String[] OPTIONS = {"List Books", "Checkout Book", "Quit"};

    private Book[] books;
    private List<Book> availableBooks;

    public Library(Book[] books){
        this.books = books;
        this.availableBooks = setAvailableBooks();
    }

    public Book[] getBooks(){ return this.books; }

    public List<Book> setAvailableBooks(){
        List<Book> available = new ArrayList<Book>();
        for(Book book: this.books){
            if(book.getStatus().equals("available")){
                available.add(book);
            }
        }
        return available;
    }

    public List<Book> getAvailableBooks(){
        this.availableBooks = setAvailableBooks();
        return this.availableBooks;
    }

    public void welcomeUser(){
        System.out.println("Welcome to Biblioteca!");
    }

    public void showAvailableBooks(){
        for (Book book: getAvailableBooks()){
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
        }else if(option.equals("Quit")) {
            quitLibrary();
        }else if(option.equals("Checkout Book")){
            askToCheckout();
        }else{
            invalidOption();
        }
    }

    public void invalidOption(){
        System.out.println("Select a valid option!");
    }

    public void quitLibrary(){
        System.out.println("Goodbye!");
    }

    public void askToCheckout(){
        System.out.println("Which book would you like to checkout?");
        Scanner sc = new Scanner(System.in);
        checkout(sc.nextLine());
    }

    public void checkout(String title){
        for(Book book: this.availableBooks){
            if(book.getTitle().equals(title)){
                book.checkout();
                System.out.println(book.getTitle() + "successfully checkout out. Thank you! Enjoy the book.");
                break;
            }
        }
        System.out.println("That book is not available.");
    }
}