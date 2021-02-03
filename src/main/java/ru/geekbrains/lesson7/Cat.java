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
        if (p.decreaseFood(this)) {
            satiety = true;
            System.out.println(name + " поел " + appetite);
        }
    }

    public boolean isSatiety() {
        return satiety;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }
}