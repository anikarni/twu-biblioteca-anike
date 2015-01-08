package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by aarni on 1/8/15.
 */
public class CheckoutOptionTest {
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123");
    Librarian librarian = new Librarian("librarian", "23");
    User[] users = {customer, librarian};
    Library library = new Library(books, movies, users);

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    public void loginCustomer(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);
        library.selectOption("Login");
    }

    @Test
    public void checkoutBookSuccessfully(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getBooks());
        option.checkout("Book Example");
        assertNotEquals(books, library.getAvailable(books));
        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book."));
    }

    @Test
    public void checkoutMovieSuccessfully(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getMovies());
        option.checkout("Title");
        assertNotEquals(movies, library.getAvailable(movies));
        assertThat(outContent.toString(), containsString("Thank you! Enjoy the movie."));
    }

    @Test
    public void checkoutBookUnsuccessfully(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getBooks());
        option.checkout("dasda");
        assertThat(outContent.toString(), containsString("That item is not available."));
    }

    @Test
    public void checkoutMovieUnsuccessfully(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getMovies());
        option.checkout("Titledsa");
        assertThat(outContent.toString(), containsString("That item is not available."));
    }
}
