package org.example;

public class PurchaseException extends RuntimeException {
    public PurchaseException() {
        super("Данная игра ещё не вышла");
    }
}
