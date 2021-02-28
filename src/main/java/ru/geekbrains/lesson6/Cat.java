package ru.geekbrains.lesson6;

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
        this.limitRun = 200;
    }

    public void swim(int distance) {
        System.out.println(name + " не может плавать.");
    }
}
