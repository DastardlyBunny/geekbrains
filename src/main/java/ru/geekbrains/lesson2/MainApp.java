package ru.geekbrains.lesson2;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MainApp {

    public static void main(String[] args) {
        int[] balanceArray = {2, 2, 2, 1, 2, 6, 10, 1};
        System.out.println(checkBalance(balanceArray));

        int[] indexerArray = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(moveArrayIndexesByNum(indexerArray, -6)));
    }

    private static boolean checkBalance(int[] array) {
        int total = IntStream.of(array).sum();
        int arrayLength = array.length;

        if (arrayLength != 0 && total % 2 != 0) {
            int summ = 0;

            for (int j : array) {
                summ += j;
                if (summ > total / 2) {
                    break;
                }
                if (summ == total / 2) {
                    return true;
                }
            }
        }

        return false;
    }

    private static int[] moveArrayIndexesByNum(int[] array, int count) {
        int arrayLength = array.length;

        if (count != 0 && count != arrayLength) {
            int absCount = Math.abs(count);

            if (absCount > arrayLength) {
                absCount -= arrayLength * (absCount / arrayLength);
            }

            if (absCount > arrayLength / 2) {
                if (count > 0) {
                    absCount -= arrayLength;
                } else {
                    absCount = arrayLength - absCount;
                }
            }

            int tmpValue;

            for (int k = 0; k < absCount; k++) {
                if (count > 0) {
                    for (int i = 0; i < arrayLength; i++) {
                        tmpValue = array[arrayLength - 1];
                        array[arrayLength - 1] = array[i];
                        array[i] = tmpValue;
                    }
                } else {
                    for (int i = arrayLength - 1; i >= 0; i--) {
                        tmpValue = array[arrayLength - 1];
                        array[arrayLength - 1] = array[i];
                        array[i] = tmpValue;
                    }
                }
            }
        }

        return array;
    }
}
