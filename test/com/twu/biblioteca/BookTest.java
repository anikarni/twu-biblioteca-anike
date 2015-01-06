package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by anikarni on 29/12/14.
 */
public class BookTest {
    Book book = new Book("Book Example", "Anike", 1991);

    @Test
    public void printsString() {
        assertEquals("Book Example, Anike, 1991", book.toString());
    }

    @Test
    public void getsTitle() {
        assertEquals("Book Example", book.getTitle());
    }

    @Test
    public void getsAuthor() { assertEquals("Anike", book.getAuthor()); }

    @Test
    public void getsYear() { assertEquals(1991, book.getYear());}

    @Test
    public void getsStatus() { assertEquals("available", book.getStatus());}

    @Test
    public void checkout() {
        book.checkout();
        assertEquals("unavailable", book.getStatus());
    }

    @Test
    public void returned(){
        book.checkout();
        book.returned();
        assertEquals("available", book.getStatus());
    }
}

