package com.twu.biblioteca;

/**
 * Created by aarni on 1/14/15.
 */
public class OptionFactory {
    public void selectOption(String option, Library library){
        Option selection = new Option();;
        if(option.equals("List Books")) {
            selection = new ListItemsOption(library, library.getBooks());
        }else if(option.equals("Login")){
            selection = new LoginOption(library);
        }else if(option.equals("Checkout Book")){
            selection = new CheckoutOption(library, library.getBooks());
        }else if(option.equals("Return Book")){
            selection = new ReturnOption(library, library.getBooks());
        }else if(option.equals("List Movies")) {
            selection = new ListItemsOption(library, library.getMovies());
        }else if(option.equals("Checkout Movie")) {
            selection = new CheckoutOption(library, library.getMovies());
        }else if(option.equals("Return Movie")) {
            selection = new ReturnOption(library, library.getMovies());
        }else if(option.equals("View My Profile")) {
            selection = new MyProfileOption(library);
        }else if(option.equals("View Customer Rentals")){
            selection = new ListRentalsOption(library);
        }else if(option.equals("Quit")) {
            selection.quitLibrary();
            return;
        }else{
            selection.invalidOption();
            return;
        }
        selection.execute();
    }
}
