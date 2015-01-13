package com.twu.biblioteca;

/**
 * Created by aarni on 1/8/15.
 */
public class MyProfileOption extends Option {
    Library library;

    public MyProfileOption(Library library){
        this.library = library;
    }

    public void execute(){
        showProfile();
    }

    public void showProfile(){
        if(library.isLoggedIn()){
            System.out.println(this.library.getCurrentUser().toString());
        }else{
            System.out.println("You must login to see your profile.");
        }
    }
}
