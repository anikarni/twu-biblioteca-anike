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


}
