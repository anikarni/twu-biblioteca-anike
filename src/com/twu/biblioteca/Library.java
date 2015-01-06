package com.twu.biblioteca;

import javax.crypto.CipherSpi;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by anikarni on 29/12/14.
 */
public class Library {
    private static String[] OPTIONS = {"List Books", "Login", "Checkout Book", "Return Book",
            "List Movies", "Checkout Movie", "Return Book", "Quit"};

    private Book[] books;
    private Movie[] movies;
    private Customer[] customers;
    private Customer currentCustomer;

    public Library(Book[] books, Movie[] movies, Customer[] customers){
        this.books = books;
        this.movies = movies;
        this.customers = customers;
        this.currentCustomer = null;
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
        }else if(option.equals("Login")){
            login();
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

    public void askToCheckout(Item[] items){
        if(isLoggedIn()) {
            System.out.println("Which item would you like to checkout? (title only)");
            Scanner sc = new Scanner(System.in);
            checkout(sc.nextLine(), items);
        }else{
            System.out.println("You must login to checkout an item.");
        }
    }

    public void checkout(String title, Item[] items){
        String type = "";
        for(Item item: getAvailable(items)){
            type = item.getType();
            if(item.getTitle().equals(title)){
                this.currentCustomer.checkout(item);
                System.out.println(item.getTitle() + " successfully checkout out. Thank you! Enjoy the " +
                        type + ".");
                return;
            }
        }
        System.out.println("That " + type + " is not available.");
    }

    public void askToReturn(Item[] items){
        if(isLoggedIn()) {
            System.out.println("Which item would you like to return? (title only)");
            Scanner sc = new Scanner(System.in);
            returnItem(sc.nextLine(), items);
        }else{
            System.out.println("You must login to return an item.");
        }
    }

    public void returnItem(String title, Item[] items){
       String type = "";
        for(Item item: items){
            type = item.getType();
            if(item.getTitle().equals(title)){
                this.currentCustomer.returnItem(item);
                System.out.println("Thank you for returning the " + type + ".");
                return;
            }
        }
        System.out.println("That is not a valid " + type + " to return.");
    }

    public void quitLibrary(){
        System.out.println("Goodbye!");
    }

    public void login(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        String username = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        findCustomer(username, password);
    }

    public void findCustomer(String username, String password){
        for(Customer customer: this.customers){
            if(customer.getUsername().equals(username)){
                if(customer.isPassword(password)){
                    System.out.println("Successfully logged in!");
                    this.currentCustomer = customer;
                    return;
                }else {
                    System.out.println("Password does not match.");
                    return;
                }
            }
        }
        System.out.println("Username does not exist.");
    }

    public Customer getCurrentCustomer(){
        return this.currentCustomer;
    }

    public boolean isLoggedIn(){
        return this.currentCustomer != null;
    }
}