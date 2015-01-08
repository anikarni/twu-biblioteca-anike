package com.twu.biblioteca;

import java.util.Scanner;

/**
 * Created by aarni on 1/8/15.
 */
public class LoginOption extends Option {
    Library library;

    public LoginOption(Library library){
        this.library = library;
    }

    public void execute(){
        login();
    }

    public void login(){
        Scanner sc = new Scanner(System.in);
        System.out.print("User Number: ");
        String userNumber = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        findUser(userNumber, password);
    }

    public void findUser(String userNumber, String password){
        for(User user: this.library.getUsers()){
            if(user.getUserNumber().equals(userNumber)){
                if(user.isPassword(password)){
                    System.out.println("Successfully logged in!");
                    this.library.setCurrentUser(user);
                    return;
                }else {
                    System.out.println("Password does not match.");
                    return;
                }
            }
        }
        System.out.println("User number does not exist.");
    }
}
