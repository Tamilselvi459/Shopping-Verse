package com.example.shopping_verse.exception;

public class InsufficientQuantityException extends RuntimeException {

  public InsufficientQuantityException(String message)
    {
        super(message);
    }
}
