package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class OptionFactoryTest {
    OptionFactory subject = new OptionFactory();
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123");
    Librarian librarian = new Librarian("librarian", "23");
    User[] users = {customer, librarian};
    Library library = new Library(books, movies, users);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void testSelectOption() throws Exception {
        subject.selectOption("List Books", library);
        assertThat(outContent.toString(), containsString("Book Example, Anike, 1991"));
    }
}