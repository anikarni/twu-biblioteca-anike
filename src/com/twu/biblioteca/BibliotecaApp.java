package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book book = new Book("Book Example", "Anike", 1991);
        Book book1 = new Book("Book Example 2", "Anon", 1765);
        Book[] books = {book, book1};
        Movie movie = new Movie("Name", 2000, "director", "2");
        Movie[] movies = {movie};
        Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123");
        Librarian librarian = new Librarian("librarian", "123");
        User[] users = {customer, librarian};
        Library library = new Library(books, movies, users);

        Scanner sc = new Scanner(System.in);
        String option = "";

        library.welcomeUser();
        library.showMenu();

        while(!option.equals("Quit")) {
            option = sc.nextLine();
            library.selectOption(option);
        }
    }
}
