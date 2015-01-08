package com.twu.biblioteca;

/**
 * Created by aarni on 1/8/15.
 */
public class UI {

    static String[] OPTIONS = {"List Books", "Login", "Checkout Book", "Return Book",
            "List Movies", "Checkout Movie", "Return Book", "View My Profile", "View Customer Rentals",
            "Quit"};

    public void showMenu(){
        System.out.println("Menu:");
        for (String option: UI.OPTIONS){
            System.out.println(option);
        }
    }
}
