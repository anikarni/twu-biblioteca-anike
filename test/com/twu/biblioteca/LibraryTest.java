package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

/**
 * Created by anikarni on 29/12/14.
 */
public class LibraryTest {
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Library library = new Library(books);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void getsBooks() {
        assertEquals(book, library.getBooks()[0]);
    }

    @Test
    public void welcomeUser(){
        library.welcomeUser();
        assertThat(outContent.toString(), containsString("Welcome to Biblioteca!"));
    }

    @Test
    public void printsAvailableBooks(){
        library.showAvailableBooks();
        assertThat(outContent.toString(), containsString("Book Example, Anike, 1991"));
    }

    @Test
    public void showsMenu(){
        library.showMenu();
        assertThat(outContent.toString(), containsString("Menu:"));
    }

    @Test
    public void showsMenuOptionListBooks(){
        library.showMenu();
        assertThat(outContent.toString(), containsString("List Books"));
    }

    @Test
    public void showsBookListWhenChosen(){
        library.selectOption("List Books");
        assertThat(outContent.toString(), containsString("Book Example, Anike, 1991"));
    }

    @Test
    public void invalidatesOption(){
        library.selectOption("List book");
        assertThat(outContent.toString(), containsString("Select a valid option!"));
    }
}
