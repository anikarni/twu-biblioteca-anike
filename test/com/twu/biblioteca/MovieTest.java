package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by aarni on 1/6/15.
 */
public class MovieTest {
    Movie movie = new Movie("Name", 2000, "director", "2");

    @Test
    public void printString(){
        assertEquals("Name, 2000, director, 2", movie.toString());
    }
}
