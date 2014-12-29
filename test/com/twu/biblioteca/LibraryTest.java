package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

/**
 * Created by anikarni on 29/12/14.
 */
public class LibraryTest {
    Book[] books = new Book[1];
    Book book = new Book("Book Example");
    Library library = new Library(books);

    @Test
    public void getsBooks() {
        books[0] = book;
        assertEquals(book, library.getBooks()[0]);
    }
}
