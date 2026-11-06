package org.example;

public class InvalidGameDataException extends RuntimeException {
    public InvalidGameDataException(String message) {
        super(message);
    }
}
