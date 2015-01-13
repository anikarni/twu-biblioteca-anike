package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

public class UITest {
    UI subject = new UI();

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
    public void welcomeUser(){
        subject.welcomeUser();
        assertThat(outContent.toString(), containsString("Welcome to Biblioteca!"));
    }

    @Test
    public void testShowsMenu() throws Exception {
        subject.showMenu();
        assertThat(outContent.toString(), containsString("Menu:"));
    }

    @Test
    public void showsMenuOptionListBooks(){
        subject.showMenu();
        assertThat(outContent.toString(), containsString("List Books"));
    }
}