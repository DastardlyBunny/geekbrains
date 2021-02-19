package ru.geekbrains2.lesson2;

import ru.geekbrains2.lesson2.exception.*;

public class MainApp {

    private static final int FIXED_ARRAY_SIZE = 4;

    private static String[][] strArr = {
        {"1", "2", "3", "4"},
        {"test", "6", "7", "8"},
        {"9", "10", "11"},
        {"13", "14", "15", "16"},
    };

    public static void main(String[] args) {
        try {
            System.out.println(checkArray(strArr));
        } catch (MyArraySizeException e) {
            System.out.printf("The array size must be %d by %d.", FIXED_ARRAY_SIZE, FIXED_ARRAY_SIZE);
        } catch (MyArrayDataException e) {
            System.out.printf("Incorrect value for [%d][%d]", e.getOuterIndex(), e.getInnerIndex());
        }
    }

    private static int checkArray(String[][] array) throws MyArraySizeException, MyArrayDataException {
        if (array.length != FIXED_ARRAY_SIZE) {
            throw new MyArraySizeException();
        }

        int summ = 0;

        for (int i = 0; i < array.length; i++) {
            if (array[i].length != FIXED_ARRAY_SIZE) {
                throw new MyArraySizeException();
            }
            for (int j = 0; j < array[i].length; j++) {
                try {
                    summ += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return summ;
    }
}
