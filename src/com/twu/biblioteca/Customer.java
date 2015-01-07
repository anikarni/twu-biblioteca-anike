package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarni on 1/6/15.
 */
public class Customer {
    private String userNumber;
    private String password;
    private List<Item> items = new ArrayList<Item>();

    public Customer(String userNumber, String password){
        this.userNumber = userNumber;
        this.password = password;
    }

    public String getUserNumber(){ return this.userNumber; }

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
        return "User Number: " + this.userNumber + "\nPassword: " + this.password;
    }
}
