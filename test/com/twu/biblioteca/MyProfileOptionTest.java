package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MyProfileOptionTest {
    Customer customer = mock(Customer.class);
    Library library = mock(Library.class);
    MyProfileOption subject = new MyProfileOption(library);

    final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    @Test
    public void showsProfile(){
        when(library.isLoggedIn()).thenReturn(true);
        when(library.getCurrentUser()).thenReturn(customer);
        when(customer.showDetails()).thenReturn("test");

        subject.showProfile();

        assertThat(outContent.toString(), containsString("test"));
    }

    @Test
    public void doesNotShowProfile(){
        when(library.isLoggedIn()).thenReturn(false);

        subject.showProfile();

        assertThat(outContent.toString(), containsString("You must login to see your profile."));
    }
}