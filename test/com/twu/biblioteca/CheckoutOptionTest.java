package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aarni on 1/8/15.
 */

public class CheckoutOptionTest {
    Library library = mock(Library.class);
    User user = mock(User.class);
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Name", 2000, "director", "2");
    Movie[] movies = {movie};
    List<Book> bookList = new ArrayList<Book>();
    List<Movie> movieList = new ArrayList<Movie>();

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
    public void checkoutBookSuccessfully(){
        bookList.add(book);
        Mockito.doReturn(bookList).when(library).getAvailable(books);
        when(library.getCurrentUser()).thenReturn(user);

        CheckoutOption subject = new CheckoutOption(library, books);
        subject.checkout("Book Example");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book."));
    }

    @Test
    public void checkoutMovieSuccessfully(){
        movieList.add(movie);
        Mockito.doReturn(movieList).when(library).getAvailable(movies);
        when(library.getCurrentUser()).thenReturn(user);

        CheckoutOption subject = new CheckoutOption(library, movies);
        subject.checkout("Name");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the movie."));
    }

    @Test
    public void checkoutBookUnsuccessfully(){
        bookList.add(book);
        Mockito.doReturn(bookList).when(library).getAvailable(books);

        CheckoutOption subject = new CheckoutOption(library, books);
        subject.checkout("Ddasddasda");

        assertThat(outContent.toString(), containsString("That item is not available."));
    }

}
