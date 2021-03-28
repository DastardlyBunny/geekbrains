package ru.geekbrains3.lesson1;

import ru.geekbrains3.lesson1.fruit.Apple;
import ru.geekbrains3.lesson1.fruit.Orange;

import java.util.ArrayList;
import java.util.Arrays;

public class MainApp {

    public static void main(String[] args) {
        Integer[] intArr = new Integer[]{1, 3, 6, 9, 10};
        String[] strArr  = new String[]{"Хорошо живет", "на свете", "Винни-пух"};

        Integer[] newIntArr = arraySwap(intArr, 3, 0);
        String[] newStrArr = arraySwap(strArr, 2, 1);

        ArrayList<Integer> newStrArr1 = toArrayList(newIntArr);
        ArrayList<String> newStrArr2 = toArrayList(newStrArr);

        Box<Apple> box = new Box<>();
        box.addFruitToBox(new Apple(), new Apple(), new Apple());

        Box<Orange> box2 = new Box<>();
        box2.addFruitToBox(new Orange(), new Orange());

        System.out.println(box.compare(box2));

        Box<Apple> box3 = new Box<>();
        box3.addFruitToBox(new Apple(), new Apple(), new Apple(), new Apple());

        box.pourFruitsToBox(box3);
    }

    private static <T> T[] arraySwap(T[] arr, int from, int to) {
        try {
            T tmpData = arr[from];
            arr[from] = arr[to];
            arr[to] = tmpData;
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
        }
        return arr;
    }

    private static <T> ArrayList<T> toArrayList(T[] elements) {
        return new ArrayList<>(Arrays.asList(elements));
    }
}
