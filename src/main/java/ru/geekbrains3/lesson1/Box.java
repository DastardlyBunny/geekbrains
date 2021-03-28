package ru.geekbrains3.lesson1;

import ru.geekbrains3.lesson1.fruit.Fruit;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {
    private final ArrayList<T> fruits;

    public Box() {
        this.fruits = new ArrayList<>();
    }

    public float getWeight() {
        return fruits.size() > 0 ? fruits.size() * fruits.get(0).getWeight() : 0;
    }

    public void addFruitToBox(T... fruits) {
        this.fruits.addAll(Arrays.asList(fruits));
    }

    public boolean compare(Box<? extends Fruit> box) {
        return Math.abs(this.getWeight() - box.getWeight()) < 0.01;
    }

    public void pourFruitsToBox(Box<T> toBox) {
        toBox.getFruits().addAll(fruits);
        fruits.clear();
    }

    private ArrayList<T> getFruits() {
        return fruits;
    }
}
