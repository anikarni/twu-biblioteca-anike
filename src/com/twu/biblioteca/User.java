package com.twu.biblioteca;

/**
 * Created by aarni on 1/7/15.
 */
public interface User {
    public boolean isPassword(String password);
    public String getUserNumber();
    public void checkout(Item item);
    public void returnItem(Item item);
    public String getType();
    public String rentalDetails();
}
