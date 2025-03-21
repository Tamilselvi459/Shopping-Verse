package com.example.shopping_verse.exception;

public class CardNotValidException extends RuntimeException {
    public CardNotValidException(String message)
    {
        super(message);
    }
}
