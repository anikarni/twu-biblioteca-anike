package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarni on 1/6/15.
 */
public class Customer {
    private String username;
    private String password;
    private List<Item> items = new ArrayList<Item>();

    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){ return this.username; }

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
}
