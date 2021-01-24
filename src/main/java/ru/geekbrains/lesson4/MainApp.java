package ru.geekbrains.lesson4;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private static final int SIZE = 5;

    private static final char[][] map = new char[SIZE][SIZE];

    private static final char DOT_HUMAN = 'X';

    private static final char DOT_AI = 'O';

    private static final char DOT_EMPTY = '*';

    private static final int WIN_COUNT = 4;

    private static final String[] userAnswer = { "ДА", "НЕТ" };

    private static Scanner scanner;

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true) {
            try {
                scanner = new Scanner(System.in);

                System.out.println("Введите число Y:");
                int y = scanner.nextInt() - 1;

                System.out.println("Введите число X:");
                int x = scanner.nextInt() - 1;

                if (canTurn()) {
                    if (humanTurn(y, x)) {
                        if (!playAgain("Человек победил! Начать заново?")) {
                            break;
                        }
                    }
                } else {
                    if (!playAgain("Ничья. Начать заново?")) {
                        break;
                    }
                }

                if (canTurn()) {
                    if (aiTurn()) {
                        if (!playAgain("ИИ победил! Начать заново?")) {
                            break;
                        }
                    }
                } else {
                    if (!playAgain("Ничья. Начать заново?")) {
                        break;
                    }
                }
                printMap();
            } catch (InputMismatchException ex) {
                printMap();
                System.out.println("Можно вводить только цифры от 1 до " + SIZE);
            } catch (Exception ex) {
                printMap();
                System.out.println(ex.getMessage());
            }
            System.out.println();
        }
    }

    private static void initMap() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                map[x][y] = DOT_EMPTY;
            }
        }
    }

    private static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.print("X");
        System.out.println();

        for (int x = 0; x < SIZE; x++) {
            System.out.print(x + 1 + " ");
            for (int y = 0; y < SIZE; y++) {
                System.out.print(map[x][y] + " ");
            }
            System.out.println();
        }

        System.out.println("Y");
        System.out.println();
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

    private static boolean humanTurn(int y, int x) throws Exception {
        if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
            if (map[y][x] != DOT_EMPTY) {
                throw new Exception("Клетка занята!");
            }
            map[y][x] = DOT_HUMAN;
        } else {
            throw new Exception("Можно вводить только цифры от 1 до " + SIZE);
        }

        return isWin(DOT_HUMAN);
    }

    private static void printMessageUserAnswer(String message) {
        StringBuilder variantsString = new StringBuilder();
        for (int i = 0; i < userAnswer.length; i++) {
            variantsString.append(i + 1);
            variantsString.append(" - ");
            variantsString.append(userAnswer[i]);

            if (i != userAnswer.length - 1) {
                variantsString.append(", ");
            }
        }

        System.out.println(message + " (" + variantsString + ")");
    }

    private static boolean aiTurn() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    map[i][j] = DOT_AI;
                    if (isWin(DOT_AI)) {
                        return true;
                    } else {
                        map[i][j] = DOT_HUMAN;
                        if (isWin(DOT_HUMAN)) {
                            map[i][j] = DOT_AI;
                            return false;
                        } else {
                            map[i][j] = DOT_EMPTY;
                        }
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

        return false;
    }

    private static boolean playAgain(String message)
    {
        printMap();
        printMessageUserAnswer(message);

        int answer;

        while (true) {
            try {
                answer = scanner.nextInt();
                switch(answer) {
                    case 1:
                        initMap();
                        printMap();
                        return true;
                    case 2:
                        return false;
                    default:
                        throw new Exception();
                }
            } catch (Exception ex) {
                printMessageUserAnswer("Введите корректный ответ! Начать заново?");
            }
        }
    }

    private static boolean isWin(char dotType) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isWinX(j, i, dotType) || isWinY(j, i, dotType) || isWinDiagonalLeft(j, i, dotType) || isWinDiagonalRight(j, i, dotType)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean isWinX (int x, int y, char dotType) {
        if (SIZE - x < WIN_COUNT) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y][x + i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private static boolean isWinY (int x, int y, char dotType) {
        if (SIZE - y < WIN_COUNT) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y + i][x] != dotType) {
                return false;
            }
        }

        return true;
    }

    private static boolean isWinDiagonalLeft (int x, int y, char dotType) {
        if (SIZE - x < WIN_COUNT || SIZE - y < WIN_COUNT) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y + i][x + i] != dotType) {
                return false;
            }
        }

        return true;
    }

    private static boolean isWinDiagonalRight(int x, int y, char dotType) {
        if (y - (WIN_COUNT - 1) < 0 || SIZE - x < WIN_COUNT) {
            return false;
        }
        for (int i = 0; i < WIN_COUNT; i++) {
            if (map[y - i][x + i] != dotType) {
                return false;
            }
        }
        return true;
    }
}
