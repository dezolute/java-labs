package org.example;

public class GameNotFoundException extends RuntimeException {
    public GameNotFoundException() {
        super("Игра не найдена");
    }
}
