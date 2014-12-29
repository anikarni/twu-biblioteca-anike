package com.twu.biblioteca;

import org.junit.Test;

/**
 * Created by anikarni on 29/12/14.
 */
public class LibraryTest {
    Book[] books = new ArrayList<Book>();
    books.add(new Book("Book Example"));
    Library library = new Library(books);

    @Test
    public void getsBooks() {
        assertEquals("Book Example", library.getBooks());
    }
}
