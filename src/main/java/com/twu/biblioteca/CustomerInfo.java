package com.twu.biblioteca;

/**
 * Created by aarni on 1/15/15.
 */
public class CustomerInfo {
    private final String name;
    private final String email;
    private final String phoneNumber;

    public CustomerInfo(String name, String email, String phoneNumber){
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
