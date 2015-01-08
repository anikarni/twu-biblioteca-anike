package com.twu.biblioteca;

/**
 * Created by aarni on 1/8/15.
 */
public class ListRentalsOption extends Option {
    Library library;

    public ListRentalsOption(Library library){
        this.library = library;
    }

    public void execute(){
        listCustomerRentals();
    }

    public void listCustomerRentals(){
        if(library.isLoggedIn() && library.isAdmin()){
            for(User user: this.library.getUsers()){
                System.out.println(user.rentalDetails());
            }
        }else{
            System.out.println("You must be an admin to view this information");
        }
    }
}
