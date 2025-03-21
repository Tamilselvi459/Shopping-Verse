package com.example.shopping_verse.exception;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
