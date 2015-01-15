package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aarni on 1/6/15.
 */
public class Customer extends User {
    private CustomerInfo customerInfo;
    private String name;
    private String email;
    private String phoneNumber;

    public Customer(String userNumber, String password, String name, String email, String phoneNumber, CustomerInfo customerInfo){
        super(userNumber, password);
        this.customerInfo = this.customerInfo;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String showDetails(){
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
