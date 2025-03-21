package com.example.shopping_verse.exception;

public class CartEmptyException extends RuntimeException {
    public CartEmptyException(String message)
    {
        super(message);
    }
}
