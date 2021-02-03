package ru.geekbrains.lesson6;

public class MainApp {
    public static void main(String[] args) {
        Animal cat = new Cat("Мурзик");
        cat.run(300);
        cat.swim(10);

        Animal dog = new Dog("Бобик");
        dog.run(300);
        dog.swim(10);

        System.out.println("Количество созданных животных: " + Animal.getCount());
    }
}
