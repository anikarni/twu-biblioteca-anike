package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by anikarni on 29/12/14.
 */
public class BookTest {
    Book book = new Book("Book Example");

    @Test
    public void getsName() {
        assertEquals("Book Example", book.getName());
    }
}
