package ru.geekbrains.lesson7;

public class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
    }

    public void eat(Plate p) {
        if (!satiety && p.decreaseFood(appetite)) {
            satiety = true;
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    public int getAppetite() {
        return appetite;
    }

    public void info() {
        System.out.println(name + (satiety ? " сыт" : " голоден"));
    }
}
