package ru.geekbrains.lesson8;

public enum GameState {
    USER ("PLAYER WIN!"),
    AI   ("AI WIN!"),
    NOBODY   ("NOBODY WIN!");

    private final String winnerMessage;

    GameState(String winnerMessage) {
        this.winnerMessage = winnerMessage;
    }

    String winnerMessage() {
        return winnerMessage;
    }

}