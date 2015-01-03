package com.twu.biblioteca;

/**
 * Created by anikarni on 29/12/14.
 */
public class Library {
    private Book[] books;

    public Library(Book[] books){
        this.books = books;
    }

    public Book[] getBooks(){
        return this.books;
    }

    public void welcomeUser(){
        System.out.println("Welcome to Biblioteca!");
    }

    public void showAvailableBooks(){
        for (Book book: this.books){
            System.out.println(book.getName());
        }
    }
}
