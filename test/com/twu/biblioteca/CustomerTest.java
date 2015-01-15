package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by aarni on 1/6/15.
 */
public class CustomerTest {
    Customer customer = new Customer("aarni", "123", "Anike", "aarni@example", "1234-123", mock(CustomerInfo.class));
    Book book = new Book("Book", "author", 1999);
    List<Item> items = new ArrayList<Item>();

    @Test
    public void validatePassword(){
        assertTrue(customer.isPassword("123"));
    }

    @Test
    public void informsCheckoutBooks(){
        items.add(book);
        customer.checkout(book);
        assertEquals(items, customer.checkedOutItems());
    }
}
