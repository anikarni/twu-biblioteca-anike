package com.twu.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        Book book = new Book("Book Example", "Anike", 1991);
        Book[] books = {book};
        Library library = new Library(books);

        Scanner sc = new Scanner(System.in);
        String option = "";

        library.welcomeUser();
        library.showMenu();

        while(!option.equals("Quit")) {
            option = sc.nextLine();
            library.selectOption(option);
            library.showMenu();
        }
    }
}
