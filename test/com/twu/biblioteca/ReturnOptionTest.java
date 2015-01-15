package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by aarni on 1/8/15.
 */
public class ReturnOptionTest {
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123", mock(CustomerInfo.class));
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
    public void returnBookSuccessfully(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getBooks());
        option.checkout("Book Example");
        ReturnOption option2 = new ReturnOption(library, library.getBooks());
        option2.returnItem("Book Example");
        assertEquals("Book Example", library.getAvailable(books).get(0).getTitle());
        assertThat(outContent.toString(), containsString("Thank you for returning the book."));
    }

    @Test
    public void returnMovieSuccessfully(){
        loginCustomer();
        CheckoutOption option = new CheckoutOption(library, library.getMovies());
        option.checkout("Title");
        ReturnOption option2 = new ReturnOption(library, library.getMovies());
        option2.returnItem("Title");
        assertEquals("Title", library.getAvailable(movies).get(0).getTitle());
        assertThat(outContent.toString(), containsString("Thank you for returning the movie."));
    }

    @Test
    public void returnBookUnsuccessfully(){
        loginCustomer();
        ReturnOption option2 = new ReturnOption(library, library.getBooks());
        option2.returnItem("Book Examplasdae");
        assertThat(outContent.toString(), containsString("That is not a valid item to return."));
    }

    @Test
    public void returnMovieUnsuccessfully(){
        loginCustomer();
        ReturnOption option2 = new ReturnOption(library, library.getMovies());
        option2.returnItem("Title321");
        assertThat(outContent.toString(), containsString("That is not a valid item to return."));
    }
}
