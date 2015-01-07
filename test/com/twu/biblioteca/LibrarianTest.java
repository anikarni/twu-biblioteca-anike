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
 * Created by aarni on 1/7/15.
 */
public class LibrarianTest {
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123");
    Librarian librarian = new Librarian("librarian", "23", "admin");
    Customer[] customers = {customer, librarian};
    Library library = new Library(books, movies, customers);

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
    public void listsCustomersItems(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);
        library.login();
        library.checkout("Name", movies);

        ByteArrayInputStream inContent2 = new ByteArrayInputStream("librarian\n23".getBytes());
        System.setIn(inContent);
        library.login();
        library.listCustomerRentals();

        assertThat(outContent.toString(), containsString("aarni\n-Name"));
    }

}
