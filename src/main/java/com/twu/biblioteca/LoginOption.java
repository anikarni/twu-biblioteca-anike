package com.twu.biblioteca;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Created by aarni on 1/8/15.
 */
public class LoginOption extends Option {
    Library library;
    private final PrintStream viewContext;

    public LoginOption(Library library){
        this.library = library;
        this.viewContext = System.out;
    }

    public LoginOption(Library library, PrintStream viewContext) {
        this.library = library;
        this.viewContext = viewContext;
    }

    public void execute(){
        login();
    }

    public void login(){
        Scanner sc = new Scanner(System.in);
        viewContext.print("User Number: ");
        String userNumber = sc.nextLine();
        viewContext.print("Password: ");
        String password = sc.nextLine();
        findUser(userNumber, password);
    }

    public void findUser(String userNumber, String password){
        for(User user: this.library.getUsers()){
            if(user.getUserNumber().equals(userNumber)){
                if(user.isPassword(password)){
                    viewContext.println("Successfully logged in!");
                    this.library.setCurrentUser(user);
                    return;
                }else {
                    viewContext.println("Password does not match.");
                    return;
                }
            }
        }
        viewContext.println("User number does not exist.");
    }
}
