package ru.geekbrains.lesson8;

public enum GameState {
    HUMAN ("PLAYER WINS!", (byte)1),
    AI   ("AI WINS!", (byte)2),
    NOBODY   ("NOBODY WINS!", (byte)0);

    private final String winnerMessage;
    private final byte winnerType;

    GameState(String winnerMessage, byte winnerType) {
        this.winnerMessage = winnerMessage;
        this.winnerType = winnerType;
    }

    String winnerMessage() {
        return winnerMessage;
    }

    byte winnerType() {
        return winnerType;
    }

}