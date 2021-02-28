package ru.geekbrains2.lesson2.exception;

public class MyArrayDataException extends Exception {
    private final int outerIndex;
    private final int innerIndex;

    public MyArrayDataException(int outerIndex, int innerIndex) {
        super("Incorrect value for array[" + outerIndex + "][" + innerIndex + "]");
        this.outerIndex = outerIndex;
        this.innerIndex = innerIndex;
    }

    public int getOuterIndex() {
        return outerIndex;
    }

    public int getInnerIndex() {
        return innerIndex;
    }
}
