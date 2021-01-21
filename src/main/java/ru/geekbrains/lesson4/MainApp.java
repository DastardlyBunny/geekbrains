package ru.geekbrains.lesson4;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainApp {

    private static final int SIZE = 3;

    private static char[][] map;

    private static final char DOT_HUMAN = 'X';

    private static final char DOT_AI = 'O';

    private static final char DOT_EMPTY = '*';

    public static void main(String[] args) {
        map = new char[SIZE][SIZE];

        initMap();
        printMap();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите число:");
            try {
                int x = scanner.nextInt() - 1;
                int y = scanner.nextInt() - 1;
                if (canTurn()) {
                    humanTurn(x, y);
                    if (checkWin(DOT_HUMAN)) {
                        System.out.println("Человек победил");
                        break;
                    }
                } else {
                    System.out.println("Ничья");
                }
                if (canTurn()) {
                    aiTurn();
                    if (checkWin(DOT_AI)) {
                        System.out.println("ИИ победил");
                        break;
                    }
                } else {
                    System.out.println("Ничья");
                }
                printMap();
            } catch (InputMismatchException ex) {
                System.out.println("Можно вводить только цифры от 1 до " + SIZE);
            }
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
    }

    private static void initMap() {
        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                map[x][y] = DOT_EMPTY;
            }
        }
    }

    private static boolean checkWin(char dot) {
//        if(map[0][0] == dot && map[0][1] == dot && map[0][2] == dot) return true;
//        if(map[1][0] == dot && map[1][1] == dot && map[1][2] == dot) return true;
//        if(map[2][0] == dot && map[2][1] == dot && map[2][2] == dot) return true;
//
//
//        if(map[0][0] == dot && map[1][0] == dot && map[2][0] == dot) return true;
//        if(map[0][1] == dot && map[1][1] == dot && map[2][1] == dot) return true;
//        if(map[0][2] == dot && map[1][2] == dot && map[2][2] == dot) return true;
//
//
//        if(map[0][0] == dot && map[1][1] == dot && map[2][2] == dot) return true;
//        if(map[2][0] == dot && map[1][1] == dot && map[0][2] == dot) return true;

        boolean result = false;

        int[] resArr = new int[4];

        /**
         * массив с результатами и в него добавлять, потом сравнить и посмотреть равен ли он SIZe и если равен, то победил
         * в одну функцию чтобы все это вставить
         * потом будет ясно куда роботу ставить свою фишку
         */
        for (int i = 0; i < SIZE; i++) {
            result = true;
            if (map[i][i] != dot) {
                result = false;
                break;
            }
        }

        if (!result) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = SIZE - 1; j >= 0; j--) {
                    result = true;
                    if (map[j][i] != dot) {
                        result = false;
                        break;
                    }
                }
            }
        }

        if (!result) {
            for (int i = 0; i < SIZE; i++) {
                result = true;
                for (int j = 0; j < SIZE; j++) {
                    if (map[i][j] != dot) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }

        if (!result) {
            for (int i = 0; i < SIZE; i++) {
                result = true;
                for (int j = 0; j < SIZE; j++) {
                    if (map[j][i] != dot) {
                        result = false;
                        break;
                    }
                }
                if (result) {
                    break;
                }
            }
        }

        return result;

    }

    private static boolean canTurn() {
        /**
         * это изменить и считать количество ходов и вычитать их из SIZE * SIZE
         */
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void aiTurn() {
        do {
            Random random = new Random();
            int x = random.nextInt(SIZE);
            int y = random.nextInt(SIZE);
            if (map[x][y] == DOT_EMPTY) {
                map[x][y] = DOT_AI;
                break;
            }
        } while (true);
    }

    private static void humanTurn(int x, int y) {
        if (x < SIZE && x >= 0 && y < SIZE && y >= 0) {
            /**
             * проверить нужно, что не занято
             */
            map[x][y] = DOT_HUMAN;
        } else {
            System.out.println("Можно вводить только цифры от 1 до " + SIZE);
        }
    }
}
