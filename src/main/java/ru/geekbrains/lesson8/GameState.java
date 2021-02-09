package ru.geekbrains.lesson8;

public class GameState {

    PlayerType state;

    private boolean checkWin(byte dotType) {
        if (isWinVY(dotType) || isWinVX(dotType)) {
            setState(dotType);
            return true;
        }
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (isWinX(i, dotType) || isWinY(i, dotType)) {
                setState(dotType);
                return true;
            }
        }

        return false;
    }

    private void setState(byte dotType) {
        GameField.setGameOn(false);
        if (dotType == GameField.DOT_HUMAN) {
            state = PlayerType.HUMAN;
        } else if (dotType == GameField.DOT_AI) {
            state = PlayerType.AI;
        } else {
            state = PlayerType.NOBODY;
        }
    }

    private boolean isWinX (int x, byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (GameField.getMap()[i][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinY (int y, byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (GameField.getMap()[y][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinVY (byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (GameField.getMap()[i][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private boolean isWinVX (byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (GameField.getMap()[i][GameField.MAP_SIZE - 1 - i] != dotType) {
                return false;
            }
        }

        return true;
    }
}
