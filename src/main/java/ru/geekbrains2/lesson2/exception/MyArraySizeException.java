package ru.geekbrains2.lesson2.exception;

public class MyArraySizeException extends Exception {
    public MyArraySizeException(int arraySize) {
        super("The array size must be " + arraySize + " by " + arraySize + ".");
    }
}
