package com.twu.biblioteca;

/**
 * Created by aarni on 1/8/15.
 */
public class ListItemsOption extends Option {
    Library library;
    Item[] items;

    public ListItemsOption(Library library, Item[] items){
        this.library = library;
        this.items = items;
    }

    public void execute(){
        listBooks();
    }

    public void listBooks(){
        for (Item item: this.items){
            System.out.println(item.toString());
        }
    }
}
