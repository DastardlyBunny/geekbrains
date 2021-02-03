package ru.geekbrains.lesson6;

public abstract class Animal {
    String name;
    int limitRun;
    int limitSwim;

    static int count;

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public void run(int distance) {
        if (distance <= limitRun) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м. Ограничение " + limitRun + " м.");
        }
    }

    public void swim(int distance) {
        if (distance <= limitSwim) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м. Ограничение " + limitSwim + " м.");
        }
    }

    public static int getCount() {
        return count;
    }
}
