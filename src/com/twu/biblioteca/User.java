package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarni on 1/7/15.
 */
public abstract class User {
    String userNumber;
    String password;
    List<Item> items = new ArrayList<Item>();

    public User(String userNumber, String password){
        this.userNumber = userNumber;
        this.password = password;
    }

    public String getUserNumber(){ return this.userNumber; }
    public String getType(){ return this.getClass().getSimpleName(); }

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

    public abstract String toString();
    public abstract String rentalDetails();
}
