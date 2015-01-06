package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book book = new Book("Book Example", "Anike", 1991);
        Book book1 = new Book("Book Example 2", "Anon", 1765);
        Book[] books = {book, book1};
        Movie movie = new Movie("Name", 2000, "director", "2");
        Movie[] movies = {movie};
        Customer customer = new Customer("aarni", "123");
        Customer[] customers = {customer};
        Library library = new Library(books, movies, customers);

        Scanner sc = new Scanner(System.in);
        String option = "";

        library.welcomeUser();

        while(!option.equals("Quit")) {
            library.showMenu();
            option = sc.nextLine();
            library.selectOption(option);
        }
    }
}
