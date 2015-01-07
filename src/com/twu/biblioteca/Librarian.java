package com.twu.biblioteca;

/**
 * Created by aarni on 1/7/15.
 */
public class Librarian implements User {
    String userNumber;
    String password;
    String type;

    public Librarian(String userNumber, String password){
        this.userNumber = userNumber;
        this.password = password;
        this.type = "admin";
    }

    public String getUserNumber(){
        return this.userNumber;
    }

    public String getType(){
        return this.type;
    }

    public boolean isPassword(String password){
        return this.password.equals(password);
    }

    public void checkout(Item item){}
    public void returnItem(Item item){}

    public String rentalDetails(){
        return "-"+ this.userNumber + "\nnone";
    }

}
