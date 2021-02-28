package ru.geekbrains.lesson8;

public class GameState {

    PlayerType state;

    private byte[][] map;

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

    public boolean isWinX (int x, byte dotType) {
        for (int y = 0; y < GameField.MAP_SIZE; y++) {
            if (map[y][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    public boolean isWinY (int y, byte dotType) {
        for (int x = 0; x < GameField.MAP_SIZE; x++) {
            if (map[y][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    public boolean isWinVX (byte dotType) {
        for (int x = 0; x < GameField.MAP_SIZE; x++) {
            if (map[x][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    public boolean isWinVY (byte dotType) {
        for (int y = 0; y < GameField.MAP_SIZE; y++) {
            if (map[y][GameField.MAP_SIZE - 1 - y] != dotType) {
                return false;
            }
        }

        return true;
    }
}
