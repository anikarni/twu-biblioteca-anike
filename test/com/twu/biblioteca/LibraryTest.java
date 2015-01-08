package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

/**
 * Created by anikarni on 29/12/14.
 */
public class LibraryTest {
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123");
    Librarian librarian = new Librarian("librarian", "23");
    User[] users = {customer, librarian};
    Library library = new Library(books, movies, users);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    public void loginCustomer(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);
        library.selectOption("Login");
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
        assertThat(outContent.toString(), containsString("Welcome to Biblioteca!"));
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
    public void chooseCheckoutBook(){
        loginCustomer();
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Checkout Book");
        assertThat(outContent.toString(), containsString("Which item would you like to checkout? (title only)"));
    }

    @Test
    public void chooseCheckoutMovie(){
        loginCustomer();
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Checkout Movie");
        assertThat(outContent.toString(), containsString("Which item would you like to checkout? (title only)"));
    }

    @Test
    public void chooseCheckoutMovieWithoutLogin(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Checkout Movie");
        assertThat(outContent.toString(), containsString("You must login to checkout an item."));
    }

    @Test
    public void chooseReturnBook(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getBooks());
        option.checkout("Book Example");
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Return Book");
        assertThat(outContent.toString(), containsString("Which item would you like to return?"));
    }

    @Test
    public void chooseReturnMovie(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getMovies());
        option.checkout("Title");
        ByteArrayInputStream inContent = new ByteArrayInputStream("Title".getBytes());
        System.setIn(inContent);
        library.selectOption("Return Movie");
        assertThat(outContent.toString(), containsString("Which item would you like to return?"));
    }

    @Test
    public void logsIn(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);
        library.selectOption("Login");
        assertEquals(customer, library.getCurrentUser());
    }

    @Test
    public void doesNotLoginWithWrongUserNumber(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni2\n123".getBytes());
        System.setIn(inContent);
        library.selectOption("Login");
        assertThat(outContent.toString(), containsString("User number does not exist."));
    }

    @Test
    public void doesNotLoginWithWrongPassword(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n1234".getBytes());
        System.setIn(inContent);
        library.selectOption("Login");
        assertThat(outContent.toString(), containsString("Password does not match."));
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

    @Test
    public void showsProfile(){
        loginCustomer();
        library.selectOption("View My Profile");
        assertThat(outContent.toString(), containsString(library.getCurrentUser().toString()));
    }
}
