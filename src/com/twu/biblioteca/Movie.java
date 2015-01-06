package com.twu.biblioteca;

/**
 * Created by aarni on 1/6/15.
 */
public class Movie {
    String title;
    int year;
    String director;
    String rating;

    public Movie(String title, int year, String director, String rating){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    @Override
    public String toString(){
        return this.title + ", " + this.year + ", " + this.director + ", " + this.rating;
    }

}
