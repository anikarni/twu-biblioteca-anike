package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by aarni on 1/6/15.
 */
public class CustomerTest {
    Customer customer = new Customer("aarni", "123");

    @Test
    public void validatePassword(){
        assertTrue(customer.isPassword("123"));
    }
}
