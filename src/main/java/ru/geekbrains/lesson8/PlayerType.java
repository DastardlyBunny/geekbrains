package ru.geekbrains.lesson8;

public enum PlayerType {
    HUMAN ((byte)1, "HUMAN WINS!"),
    AI ((byte)2, "AI WINS!"),
    NOBODY ((byte)0, "NOBODY WINS!");

    private final String winnerMessage;
    private final byte playerType;

    PlayerType(byte playerType, String winnerMessage) {
        this.playerType = playerType;
        this.winnerMessage = winnerMessage;
    }

    String getWinnerMessage() {
        return winnerMessage;
    }

    byte getPlayerType() {
        return playerType;
    }

}