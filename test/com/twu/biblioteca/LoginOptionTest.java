package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

public class LoginOptionTest {
    Customer customer = mock(Customer.class);
    User[] users = {customer};
    Book[] books = {mock(Book.class)};
    Movie[] movies = {mock(Movie.class)};
    Library library = spy(new Library(books, movies, users));
    private PrintStream viewContext = mock(PrintStream.class);
    LoginOption option = spy(new LoginOption(library, viewContext));

    @Test
    public void testLogin(){
        Mockito.doNothing().when(option).findUser("aarni", "123");
        ByteArrayInputStream inContent = new ByteArrayInputStream("aarni\n123".getBytes());
        System.setIn(inContent);

        option.login();

        verify(option).findUser("aarni", "123");
    }

    @Test
    public void findsUser(){
        when(customer.getUserNumber()).thenReturn("123");
        when(customer.isPassword("123")).thenReturn(true);

        option.findUser("123", "123");

        verify(viewContext).println(contains("Successfully logged in!"));
    }

    @Test
    public void doesNotLoginWithWrongUserNumber(){
        when(customer.getUserNumber()).thenReturn("12123");

        option.findUser("123", "123");

        verify(viewContext).println(contains("User number does not exist."));
    }

    @Test
    public void doesNotLoginWithWrongPassword(){
        when(customer.getUserNumber()).thenReturn("123");
        when(customer.isPassword("123")).thenReturn(false);

        option.findUser("123", "123");

        verify(viewContext).println(contains("Password does not match."));
    }
}