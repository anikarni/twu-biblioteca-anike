package com.twu.biblioteca;


/**
 * Created by aarni on 1/6/15.
 */
public class Customer extends User {
    private CustomerInfo customerInfo;

    public Customer(String userNumber, String password, CustomerInfo customerInfo){
        super(userNumber, password);
        this.customerInfo = customerInfo;
    }

    @Override
    public String showDetails(){
        return "Name: " + customerInfo.getName() + "\nEmail: "
                + customerInfo.getEmail() + "\nPhone:" + customerInfo.getPhoneNumber();
    }

    public String rentalDetails(){
        String rentalDetails = "-" + customerInfo.getName() + ", " + customerInfo.getEmail()
                + ", " + customerInfo.getPhoneNumber();
        for (Item item: this.items){
            rentalDetails += "\n" + item.getTitle();
        }
        return rentalDetails;
    }
}
