package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anikarni on 29/12/14.
 */
public class Library {
    private static String[] OPTIONS = {"List Books", "Login", "Checkout Book", "Return Book",
            "List Movies", "Checkout Movie", "Return Book", "View My Profile", "View Customer Rentals",
            "Quit"};

    private Book[] books;
    private Movie[] movies;
    private User[] users;
    private User currentUser;

    public Library(Book[] books, Movie[] movies, User[] users){
        this.books = books;
        this.movies = movies;
        this.users = users;
        this.currentUser = null;
    }

    public Book[] getBooks(){ return this.books; }
    public Movie[] getMovies(){ return this.movies; }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public void setCurrentUser(User user){
        this.currentUser = user;
    }

    public User[] getUsers(){
        return this.users;
    }

    public List<Item> getAvailable(Item[] items){
        List<Item> available = new ArrayList<Item>();
        for(Item i: items){
            if(i.getStatus().equals("available")){
                available.add(i);
            }
        }
        return available;
    }

    public void welcomeUser(){
        System.out.println("Welcome to Biblioteca!");
    }

    public void showMenu(){
        System.out.println("Menu:");
        for (String option: OPTIONS){
            System.out.println(option);
        }
    }

    public void selectOption(String option){
        Option selection = new Option();
        selection.selectOption(option, this);
    }

    public boolean isLoggedIn(){
        return this.currentUser != null;
    }

    public boolean isAdmin(){
        return this.currentUser.getType().equals("Librarian");
    }
}