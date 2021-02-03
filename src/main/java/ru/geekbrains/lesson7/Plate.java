package ru.geekbrains.lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public boolean decreaseFood(Cat cat) {
        if (cat.getAppetite() <= food) {
            food -= cat.getAppetite();
            return true;
        }
        System.out.println("В тарелке не хватило еды, чтобы " + cat.getName() + " мог поесть " + cat.getAppetite());
        return false;
    }

    public void info() {
        System.out.println("Еды в тарелке: " + food);
    }

    public void addFood(Cat cat) {
        int foodAmount = cat.getAppetite() - food;
        food += foodAmount;
        System.out.println("Добавили в тарелку " + foodAmount + " еды.");
    }
}
