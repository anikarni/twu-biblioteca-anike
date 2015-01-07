package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarni on 1/6/15.
 */
public class Customer implements User {
    private String userNumber;
    private String password;
    private String name;
    private String email;
    private String phoneNumber;
    private String type;
    private List<Item> items = new ArrayList<Item>();

    public Customer(String userNumber, String password, String name, String email, String phoneNumber){
        this.userNumber = userNumber;
        this.password = password;
        this.name = name;
        this. email = email;
        this.phoneNumber = phoneNumber;
        this.type = "customer";
    }

    public String getUserNumber(){ return this.userNumber; }
    public String getType(){ return this.type; }

    public boolean isPassword(String password){
        return this.password.equals(password);
    }

    public List<Item> checkedOutItems(){
        return this.items;
    }

    public void checkout(Item item){
        this.items.add(item);
        item.checkout();
    }

    public void returnItem(Item item){
        int i = items.indexOf(item);
        this.items.remove(i);
        item.returned();
    }

    @Override
    public String toString(){
        return "Name: " + this.name + "\nEmail: " + this.email + "\nPhone:" + this.phoneNumber;
    }

    public String rentalDetails(){
        String rentalDetails = "-" + this.name + ", " + this.email + ", " + this.phoneNumber;
        for (Item item: this.items){
            rentalDetails += "\n" + item.getTitle();
        }
        return rentalDetails;
    }
}
