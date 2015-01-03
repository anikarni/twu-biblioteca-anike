package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

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
        assertArrayEquals(books, library.getBooks());
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

    @Test
    public void quits(){
        library.selectOption("Quit");
        assertThat(outContent.toString(), containsString("Goodbye!"));
    }

    @Test
    public void chooseCheckout(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Checkout Book");
        assertThat(outContent.toString(), containsString("Which book would you like to checkout?"));
    }

    @Test
    public void checkoutBookSuccessfully(){
        library.checkout("Book Example");
        assertNotEquals(books, library.getAvailableBooks());
        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book."));
    }

    @Test
    public void checkoutBookUnsuccessfully(){
        library.checkout("fdfsd");
        assertThat(outContent.toString(), containsString("That book is not available."));
    }

    @Test
    public void chooseReturn(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Return Book");
        assertThat(outContent.toString(), containsString("Which book would you like to return?"));
    }

    @Test
    public void returnBookSuccessfully(){
        library.checkout("Book Example");
        library.returnBook("Book Example");
        assertEquals("Book Example", library.getAvailableBooks().get(0).getTitle());
        assertThat(outContent.toString(), containsString("Thank you for returning the book."));
    }

    @Test
    public void returntBookUnsuccessfully(){
        library.returnBook("fdfsd");
        assertThat(outContent.toString(), containsString("That is not a valid book to return."));
    }
}
