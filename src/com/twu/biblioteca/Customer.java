package com.twu.biblioteca;

/**
 * Created by aarni on 1/6/15.
 */
public class Customer {
    private String username;
    private String password;

    public Customer(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername(){ return this.username; }

    public boolean isPassword(String password){
        return this.password.equals(password);
    }
}
