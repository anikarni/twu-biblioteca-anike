package com.twu.biblioteca;

/**
 * Created by aarni on 1/6/15.
 */
public class Movie implements Item {
    String title;
    int year;
    String director;
    String rating;
    String status;
    String type;

    public Movie(String title, int year, String director, String rating){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
        this.status = "available";
        this.type = "movie";
    }

    @Override
    public String toString(){
        return this.title + ", " + this.year + ", " + this.director + ", " + this.rating;
    }

    public String getTitle(){ return this.title; }
    public String getStatus(){ return this.status; }
    public String getType(){ return this.type; }

    public void checkout(){ this.status = "unavailable"; }

    public void returned(){ this.status = "available"; }
}
