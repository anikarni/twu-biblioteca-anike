package com.twu.biblioteca;

/**
 * Created by aarni on 1/7/15.
 */
public class Librarian extends User {

    public Librarian(String userNumber, String password){
        super(userNumber, password);
    }

    @Override
    public String toString(){
        return "User Number: " + this.userNumber + "\nType: " + this.getType();
    }

    public String rentalDetails(){
        String rentalDetails = "-" + this.userNumber;
        for (Item item: this.items){
            rentalDetails += "\n" + item.getTitle();
        }
        return rentalDetails;
    }

}
