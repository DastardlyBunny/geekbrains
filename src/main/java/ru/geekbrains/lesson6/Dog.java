package ru.geekbrains.lesson6;

public class Dog extends Animal {
    public Dog(String name) {
        super(name);
        this.limitRun = 500;
        this.limitSwim = 10;
    }
}
