package ru.geekbrains.lesson8;

public enum PlayerType {
    HUMAN ("PLAYER WINS!", (byte)1),
    AI   ("AI WINS!", (byte)2),
    NOBODY   ("NOBODY WINS!", (byte)0);

    private final String winnerMessage;
    private final byte playerType;

    PlayerType(String winnerMessage, byte playerType) {
        this.winnerMessage = winnerMessage;
        this.playerType = playerType;
    }

    String getWinnerMessage() {
        return winnerMessage;
    }

    byte getPlayerType() {
        return playerType;
    }

}