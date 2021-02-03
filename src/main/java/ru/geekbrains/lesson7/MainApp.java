package ru.geekbrains.lesson7;

public class MainApp {
    public static void main(String[] args) {
        Cat[] cat = {
                new Cat("Barsik1", 30),
                new Cat("Barsik2", 40),
                new Cat("Barsik3", 35),
                new Cat("Barsik4", 20),
        };

        Plate plate = new Plate(100);

        for (Cat c : cat) {
            c.eat(plate);
            if (!c.isSatiety()) {
                plate.addFood(c.getAppetite());
                c.eat(plate);
            }
            c.info();
        }
    }
}
