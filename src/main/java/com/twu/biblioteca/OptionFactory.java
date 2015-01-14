package com.twu.biblioteca;

/**
 * Created by aarni on 1/14/15.
 */
public class OptionFactory {
    public static final String LIST_BOOKS = "List Books";
    public static final String LOGIN = "Login";
    public static final String CHECKOUT_BOOK = "Checkout Book";
    public static final String RETURN_BOOK = "Return Book";
    public static final String LIST_MOVIES = "List Movies";
    public static final String CHECKOUT_MOVIE = "Checkout Movie";
    public static final String RETURN_MOVIE = "Return Movie";
    public static final String VIEW_MY_PROFILE = "View My Profile";
    public static final String VIEW_CUSTOMER_RENTALS = "View Customer Rentals";
    public static final String QUIT = "Quit";

    public Option selectOption(Option option, Library library){
        Option selection = new Option();

        if(option.is(LIST_BOOKS)) {
            selection = new ListItemsOption(library, library.getBooks());
        }else if(option.is(LOGIN)){
            selection = new LoginOption(library);
        }else if(option.is(CHECKOUT_BOOK)){
            selection = new CheckoutOption(library, library.getBooks());
        }else if(option.is(RETURN_BOOK)){
            selection = new ReturnOption(library, library.getBooks());
        }else if(option.is(LIST_MOVIES)) {
            selection = new ListItemsOption(library, library.getMovies());
        }else if(option.is(CHECKOUT_MOVIE)) {
            selection = new CheckoutOption(library, library.getMovies());
        }else if(option.is(RETURN_MOVIE)) {
            selection = new ReturnOption(library, library.getMovies());
        }else if(option.is(VIEW_MY_PROFILE)) {
            selection = new MyProfileOption(library);
        }else if(option.is(VIEW_CUSTOMER_RENTALS)){
            selection = new ListRentalsOption(library);
        }else if(option.is(QUIT)) {
            selection = new QuitOption(library);
        }
        return selection;
    }
}
