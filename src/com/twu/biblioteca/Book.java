package com.twu.biblioteca;

/**
 * Created by anikarni on 29/12/14.
 */
public class Book {
    private String title;
    private String author;
    private int year;
    private String status;

    public Book(String title, String author, int year){
        this.title = title;
        this.author = author;
        this.year = year;
        this.status = "available";
    }

    public String getTitle(){
        return this.title;
    }

    public String getAuthor() { return this.author; }

    public int getYear() { return this.year; }

    public String getStatus() { return this.status; }

    public void checkout() {
        this.status = "unavailable";
    }

    public void returned(){
        this.status = "available";
    }
}
