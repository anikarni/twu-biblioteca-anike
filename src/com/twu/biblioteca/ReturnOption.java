package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by aarni on 1/8/15.
 */
public class ReturnOption extends Option {
    Library library;
    Item[] items;

    public ReturnOption(Library library, Item[] items){
        this.library = library;
        this.items = items;
    }

    public void execute(){
        askToReturn();
    }

    public void askToReturn(){
        if(library.isLoggedIn()) {
            System.out.println("Which item would you like to return? (title only)");
            Scanner sc = new Scanner(System.in);
            returnItem(sc.nextLine());
        }else{
            System.out.println("You must login to return an item.");
        }
    }

    public void returnItem(String title){
        for(Item item: this.items){
            if(item.getTitle().equals(title)){
                this.library.getCurrentUser().returnItem(item);
                System.out.println("Thank you for returning the " + item.getType() + ".");
                return;
            }
        }
        System.out.println("That is not a valid item to return.");
    }
}
