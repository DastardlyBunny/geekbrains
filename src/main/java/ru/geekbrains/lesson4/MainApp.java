package ru.geekbrains.lesson4;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private static final int SIZE = 5;

    private static char[][] map = new char[SIZE][SIZE];;

    private static final char DOT_HUMAN = 'X';

    private static final char DOT_AI = 'O';

    private static final char DOT_EMPTY = '*';

    private static final int WIN_COUNT = 4;

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("Введите число Y:");
                int y = scanner.nextInt() - 1;
                System.out.println("Введите число X:");
                int x = scanner.nextInt() - 1;
                if (canTurn()) {
                    if (!humanTurn(y, x)) {
                        continue;
                    }
                    if (checkWin(DOT_HUMAN)) {
                        System.out.println("Человек победил");
                        printMap();
                        break;
                    }
                } else {
                    System.out.println("Ничья");
                }
                if (canTurn()) {
                    aiTurn();
                    if (checkWin(DOT_AI)) {
                        System.out.println("ИИ победил");
                        printMap();
                        break;
                    }
                } else {
                    System.out.println("Ничья");
                }
                printMap();
            } catch (InputMismatchException ex) {
                System.out.println("Можно вводить только цифры от 1 до " + SIZE);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }

        System.out.println();

        for (int x = 0; x < SIZE; x++) {
            System.out.print(x + 1 + " ");
            for (int y = 0; y < SIZE; y++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initMap() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                map[x][y] = DOT_EMPTY;
            }
        }
    }

    private static boolean checkWin(char dotType) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (checkWinX(j, i, dotType) || checkWinY(j, i, dotType) ||
                    checkWinDiagonalLeft(j, i, dotType) || checkWinDiagonalRight(j, i, dotType)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkWinX (int x, int y, char dotType) {
        if (x + WIN_COUNT - 1 > SIZE - 1 || y > SIZE - 1) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y][x + i] != dotType) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWinY (int x, int y, char dotType) {
        if (x > SIZE - 1 || y + WIN_COUNT - 1 > SIZE - 1) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y + i][x] != dotType) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWinDiagonalLeft (int x, int y, char dotType) {
        int coordX = x + WIN_COUNT - 1;
        int coordY = y + WIN_COUNT - 1;
        if (coordX > SIZE - 1 || coordY > SIZE - 1) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y + i][x + i] != dotType) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkWinDiagonalRight(int x, int y, char dotType) {
        int coordX = x + WIN_COUNT - 1;
        int coordY = y - WIN_COUNT - 1;

        if (coordX < 0 || coordY < 0 || coordX > SIZE - 1 || coordY > SIZE - 1) {
            return false;
        }

        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y - i][x + i] != dotType) {
                return false;
            }
        }
        return true;
    }

    private static boolean canTurn() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void aiTurn() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_AI;
                    if (!checkWin(DOT_AI)) {
                        map[i][j] = DOT_EMPTY;
                    } else {
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_HUMAN;
                    if (!checkWin(DOT_HUMAN)) {
                        map[i][j] = DOT_EMPTY;
                    } else {
                        map[i][j] = DOT_AI;
                        return;
                    }
                }
            }
        }

        Random random = new Random();

        do {
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            if (map[x][y] == DOT_EMPTY) {
                map[x][y] = DOT_AI;
                break;
            }
        } while (true);
    }

    private static boolean humanTurn(int y, int x) throws Exception {
        if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
            if (map[y][x] != DOT_EMPTY) {
                throw new Exception("Клетка занята!");
            }
            map[y][x] = DOT_HUMAN;
        } else {
            throw new Exception("Можно вводить только цифры от 1 до " + SIZE);
        }

        return true;
    }
}
