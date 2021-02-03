package ru.geekbrains.lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(int appetite) {
        if (appetite <= food) {
            food -= appetite;
            return true;
        }
        return false;
    }

    public void addFood(int appetite) {
        food += appetite;
    }
}
