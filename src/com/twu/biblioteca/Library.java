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
        //interface
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
        }else if(option.equals("Return Movie")) {
            askToReturn(movies);
        }else if(option.equals("View My Profile")) {
            showProfile();
        }else if(option.equals("View Customer Rentals")){
            listCustomerRentals();
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
        for(Item item: getAvailable(items)){
            if(item.getTitle().equals(title)){
                this.currentUser.checkout(item);
                System.out.println(item.getTitle() + " successfully checkout out. Thank you! Enjoy the " +
                        item.getType() + ".");
                return;
            }
        }
        System.out.println("That item is not available.");
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
        for(Item item: items){
            if(item.getTitle().equals(title)){
                this.currentUser.returnItem(item);
                System.out.println("Thank you for returning the " + item.getType() + ".");
                return;
            }
        }
        System.out.println("That is not a valid item to return.");
    }

    public void quitLibrary(){
        System.out.println("Goodbye!");
    }

    public void login(){
        Scanner sc = new Scanner(System.in);
        System.out.print("User Number: ");
        String userNumber = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        findUser(userNumber, password);
    }

    public void findUser(String userNumber, String password){
        for(User user: this.users){
            if(user.getUserNumber().equals(userNumber)){
                if(user.isPassword(password)){
                    System.out.println("Successfully logged in!");
                    this.currentUser = user;
                    return;
                }else {
                    System.out.println("Password does not match.");
                    return;
                }
            }
        }
        System.out.println("User number does not exist.");
    }

    public User getCurrentUser(){
        return this.currentUser;
    }

    public boolean isLoggedIn(){
        return this.currentUser != null;
    }

    public void showProfile(){
        if(isLoggedIn()){
            System.out.println(this.currentUser.toString());
        }else{
            System.out.println("You must login to see your profile.");
        }
    }

    public boolean isAdmin(){
        return this.currentUser.getType().equals("Librarian");
    }

    public void listCustomerRentals(){
        if(isLoggedIn() && isAdmin()){
            for(User user: this.users){
                System.out.println(user.rentalDetails());
            }
        }else{
            System.out.println("You must be an admin to view this information");
        }
    }
}