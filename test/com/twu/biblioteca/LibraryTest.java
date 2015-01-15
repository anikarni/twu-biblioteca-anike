package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by anikarni on 29/12/14.
 */
public class LibraryTest {
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123", mock(CustomerInfo.class));
    Librarian librarian = new Librarian("librarian", "23");
    User[] users = {customer, librarian};
    Library library = new Library(books, movies, users);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void loginCustomer(){
        library.setCurrentUser(customer);
    }

    @Before
    public void setUpMock() {
        library.ui = mock(UI.class);
        library.selection = mock(Option.class);
    }

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
    public void getsMovies() {
        assertArrayEquals(movies, library.getMovies());
    }

    @Test
    public void welcomeUser(){
        library.welcomeUser();
        verify(library.ui).welcomeUser();
    }

    @Test
    public void showsMenu(){
        library.showMenu();
        verify(library.ui).showMenu();
    }

    @Test
    public void testSelectOption(){
        library.selectOption("List Books");
        verify(library.selection).selectOption("List Books", library);
    }

    @Test
    public void isLoggedInWhenUserLogsIn(){
        loginCustomer();
        assertTrue(library.isLoggedIn());
    }

    @Test
    public void isNotLoggedIn(){
        assertFalse(library.isLoggedIn());
    }

}
