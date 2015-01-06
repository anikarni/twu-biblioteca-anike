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
    Movie movie = new Movie("Title", 2000, "director", "2");
    Movie[] movies = {movie};
    Customer customer = new Customer("aarni", "123");
    Customer[] customers = {customer};
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
    public void printsBooks(){
        library.listItems(books);
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
    public void chooseCheckoutBook(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Checkout Book");
        assertThat(outContent.toString(), containsString("Which item would you like to checkout? (title only)"));
    }

    @Test
    public void chooseCheckoutMovie(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Checkout Movie");
        assertThat(outContent.toString(), containsString("Which item would you like to checkout? (title only)"));
    }

    @Test
    public void checkoutBookSuccessfully(){
        library.checkout("Book Example", books);
        assertNotEquals(books, library.getAvailable(books));
        assertThat(outContent.toString(), containsString("Thank you! Enjoy the book."));
    }

    @Test
    public void checkoutMovieSuccessfully(){
        library.checkout("Title", movies);
        assertNotEquals(movies, library.getAvailable(movies));
        assertThat(outContent.toString(), containsString("Thank you! Enjoy the movie."));
    }

    @Test
    public void checkoutBookUnsuccessfully(){
        library.checkout("fdfsd", books);
        assertThat(outContent.toString(), containsString("That book is not available."));
    }

    @Test
    public void checkoutMovieUnsuccessfully(){
        library.checkout("fdfsd", movies);
        assertThat(outContent.toString(), containsString("That movie is not available."));
    }

    @Test
    public void chooseReturnBook(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Book Example".getBytes());
        System.setIn(inContent);
        library.selectOption("Return Book");
        assertThat(outContent.toString(), containsString("Which item would you like to return?"));
    }

    @Test
    public void chooseReturnMovie(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("Title".getBytes());
        System.setIn(inContent);
        library.selectOption("Return Movie");
        assertThat(outContent.toString(), containsString("Which item would you like to return?"));
    }

    @Test
    public void returnBookSuccessfully(){
        library.checkout("Book Example", books);
        library.returnItem("Book Example", books);
        assertEquals("Book Example", library.getAvailable(books).get(0).getTitle());
        assertThat(outContent.toString(), containsString("Thank you for returning the book."));
    }

    @Test
    public void returnMovieSuccessfully(){
        library.checkout("Title", movies);
        library.returnItem("Title", movies);
        assertEquals("Title", library.getAvailable(movies).get(0).getTitle());
        assertThat(outContent.toString(), containsString("Thank you for returning the movie."));
    }

    @Test
    public void returnBookUnsuccessfully(){
        library.returnItem("fdfsd", books);
        assertThat(outContent.toString(), containsString("That is not a valid book to return."));
    }

    @Test
    public void returnMovieUnsuccessfully(){
        library.returnItem("fdfsd", movies);
        assertThat(outContent.toString(), containsString("That is not a valid movie to return."));
    }

    @Test
    public void logsIn(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);
        library.login();
        assertEquals(customer, library.getCurrentCustomer());
    }

    @Test
    public void doesNotLoginWithWrongUsername(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni2\n123".getBytes());
        System.setIn(inContent);
        library.login();
        assertThat(outContent.toString(), containsString("Username does not exist."));
    }

    @Test
    public void doesNotLoginWithWrongPassword(){
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n1234".getBytes());
        System.setIn(inContent);
        library.login();
        assertThat(outContent.toString(), containsString("Password does not match."));
    }
}
