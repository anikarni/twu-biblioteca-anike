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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by aarni on 1/8/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class CheckoutOptionTest {
    @Mock Library library;
    @Mock User user;
    Book book = new Book("Book Example", "Anike", 1991);
    Book[] books = {book};
    Movie movie = new Movie("Name", 2000, "director", "2");
    Movie[] movies = {movie};
    List<Item> bookList = new ArrayList<Item>();
    List<Item> movieList = new ArrayList<Item>();

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
        when(library.getAvailable(books)).thenReturn(bookList);
        when(library.getCurrentUser()).thenReturn(user);

        CheckoutOption subject = new CheckoutOption(library, books);
        subject.checkout("Book Example");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book."));
    }

    @Test
    public void checkoutMovieSuccessfully(){
        movieList.add(movie);
        when(library.getAvailable(movies)).thenReturn(movieList);
        when(library.getCurrentUser()).thenReturn(user);

        CheckoutOption subject = new CheckoutOption(library, movies);
        subject.checkout("Name");

        assertThat(outContent.toString(), containsString("Thank you! Enjoy the movie."));
    }

    @Test
    public void checkoutBookUnsuccessfully(){
        bookList.add(book);
        doReturn(bookList).when(library).getAvailable(books);

        CheckoutOption subject = new CheckoutOption(library, books);
        subject.checkout("Ddasddasda");

        assertThat(outContent.toString(), containsString("That item is not available."));
    }

}
