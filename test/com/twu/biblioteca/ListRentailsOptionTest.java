package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aarni on 1/8/15.
 */
public class ListRentailsOptionTest {
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
    public void listsCustomersItems(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);
        library.selectOption("Login");
        CheckoutOption option = new CheckoutOption(library, library.getMovies());
        option.checkout("Title");

        ByteArrayInputStream inContent2 = new ByteArrayInputStream("librarian\n23".getBytes());
        System.setIn(inContent2);
        library.selectOption("Login");
        library.selectOption("View Customer Rentals");

        assertThat(outContent.toString(), containsString("-Anike, aarni@example, 1234-123\nTitle"));
    }
}
