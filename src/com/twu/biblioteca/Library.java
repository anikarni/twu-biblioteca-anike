package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anikarni on 29/12/14.
 */
public class Library {
    private static String[] OPTIONS = {"List Books", "Checkout Book", "Return Book",
            "List Movies", "Checkout Movie", "Return Book", "Quit"};

    private Book[] books;
    private Movie[] movies;

    public Library(Book[] books, Movie[] movies){
        this.books = books;
        this.movies = movies;
    }

    public Book[] getBooks(){ return this.books; }
    public Movie[] getMovies(){ return this.movies; }

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

    public void listItems(Item[] items){
        for (Item item: items){
            System.out.println(item.toString());
        }
    }

    public void showMenu(){
        System.out.println("Menu:");
        for (String option: OPTIONS){
            System.out.println(option);
        }
    }

    public void selectOption(String option){
        if(option.equals("List Books")) {
            listItems(books);
        }else if(option.equals("Checkout Book")){
            askToCheckout(books);
        }else if(option.equals("Return Book")){
            askToReturn(books);
        }else if(option.equals("List Movies")) {
            listItems(movies);
        }else if(option.equals("Checkout Movie")) {
            askToCheckout(movies);
        }else if(option.equals("Return Movie")){
            askToReturn(movies);
        }else if(option.equals("Quit")) {
            quitLibrary();
        }else{
            invalidOption();
        }
    }

    public void invalidOption(){
        System.out.println("Select a valid option!");
    }

    public void quitLibrary(){
        System.out.println("Goodbye!");
    }

    public void askToCheckout(Item[] items){
        System.out.println("Which item would you like to checkout? (title only)");
        Scanner sc = new Scanner(System.in);
        checkout(sc.nextLine(), items);
    }

    public void checkout(String title, Item[] items){
        String type = "";
        for(Item item: getAvailable(items)){
            type = item.getType();
            if(item.getTitle().equals(title)){
                item.checkout();
                System.out.println(item.getTitle() + " successfully checkout out. Thank you! Enjoy the " +
                        type + ".");
                return;
            }
        }
        System.out.println("That " + type + " is not available.");
    }

    public void askToReturn(Item[] items){
        System.out.println("Which item would you like to return? (title only)");
        Scanner sc = new Scanner(System.in);
        returnItem(sc.nextLine(), items);
    }

    public void returnItem(String title, Item[] items){
       String type = "";
        for(Item item: items){
            type = item.getType();
            if(item.getTitle().equals(title)){
                item.returned();
                System.out.println("Thank you for returning the " + type + ".");
                return;
            }
        }
        System.out.println("That is not a valid " + type + " to return.");
    }
}