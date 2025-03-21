package com.example.shopping_verse.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message)
    {
        super(message);
    }
}
