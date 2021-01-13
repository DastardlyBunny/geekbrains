package ru.geekbrains.lesson1;

public class Main {

    int i = 2147483647;
    byte b = 127;
    short sh = 32767;
    boolean bo = true;
    float f = 1.23456f;
    double d = 1.22;
    char c = 'a';
    String s = "Test";
    long l = 9223372036854775807L;

    public static void main(String[] args) {
        System.out.println(calculate(1,2,3,1));
        System.out.println(checkNumber(10));
        isPositive(7);
        System.out.println(isNegative(-10));
        sayHello("Анна");
        isLeapYear(1100);
    }

    private static float calculate(float a, float b, float c, float d) {
        float result = 0;
        if(d == 0) {
            System.out.println("Cannot be divided by zero. The parameter 'd' must be greater than 0");
        } else {
            result = a * (b + (c / d));
        }
        return result;
    }

    private static boolean checkNumber(int number) {
        return number > 10 && number <= 20;
    }

    private static void isPositive(int number) {
        if (number >= 0) {
            System.out.println("Positive");
        } else {
            System.out.println("Negative");
        }
    }

    private static boolean isNegative(int number) {
        return number < 0;
    }

    private static void sayHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    private static void isLeapYear(int year) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            System.out.println(year + " - високосный год");
        } else {
            System.out.println(year + " - не високосный год");
        }
    }
}
