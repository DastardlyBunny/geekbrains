package ru.geekbrains.lesson8;

public class GameState {

    PlayerType state;

    private byte[][] map;

    public boolean checkWin(byte[][] map, byte dotType) {
        this.map = map;
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

    public String getWinnerMessage() {
        return state.getWinnerMessage();
    }

    public void setState(byte dotType) {
        if (dotType == GameField.DOT_HUMAN) {
            state = PlayerType.HUMAN;
        } else if (dotType == GameField.DOT_AI) {
            state = PlayerType.AI;
        } else {
            state = PlayerType.NOBODY;
        }
    }

    public boolean isWinX (int x, byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (map[i][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    public boolean isWinY (int y, byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (map[y][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    public boolean isWinVY (byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (map[i][i] != dotType) {
                return false;
            }
        }

        return true;
    }

    public boolean isWinVX (byte dotType) {
        for (int i = 0; i < GameField.MAP_SIZE; i++) {
            if (map[i][GameField.MAP_SIZE - 1 - i] != dotType) {
                return false;
            }
        }

        return true;
    }
}
