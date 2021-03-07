package ru.geekbrains2.lesson5;

import java.util.Arrays;

public class MainApp {

    static final int size = 10_000_000;
    static final int h = size / 2;

    public static void main(String[] args) {
        getArray();
        getArrayThread();
    }

    public static void getArray() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        arrayMath(arr, size, 0);
        System.out.println(System.currentTimeMillis() - a);
    }

    private static void arrayMath(float[] arr, int size, int start) {
        for (int i = 0; i < size; i++) {
            arr[i + start] = (float)(arr[i + start] * Math.sin(0.2f + (i + start) / 5.0f) *
                    Math.cos(0.2f + (i + start) / 5.0f) * Math.cos(0.4f + (i + start) / 2.0f));
        }
    }

    public static void getArrayThread() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1);
        long a = System.currentTimeMillis();
        Thread t1 = new Thread(() -> arrayMath(arr, h, 0));
        Thread t2 = new Thread(() -> arrayMath(arr, h, h));

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(System.currentTimeMillis() - a);
    }
}
