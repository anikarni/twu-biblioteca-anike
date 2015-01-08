package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by aarni on 1/8/15.
 */
public class CheckoutOption extends Option {
    Library library;
    Item[] items;

    public CheckoutOption(Library library, Item[] items){
        this.library = library;
        this.items = items;
    }

    public void execute(){
        askToCheckout();
    }

    public void askToCheckout(){
        if(this.library.isLoggedIn()) {
            System.out.println("Which item would you like to checkout? (title only)");
            Scanner sc = new Scanner(System.in);
            checkout(sc.nextLine());
        }else{
            System.out.println("You must login to checkout an item.");
        }
    }

    public void checkout(String title){
        for(Item item: this.library.getAvailable(this.items)){
            if(item.getTitle().equals(title)){
                this.library.getCurrentUser().checkout(item);
                System.out.println(item.getTitle() + " successfully checkout out. Thank you! Enjoy the " +
                        item.getType() + ".");
                return;
            }
        }
        System.out.println("That item is not available.");
    }
}
