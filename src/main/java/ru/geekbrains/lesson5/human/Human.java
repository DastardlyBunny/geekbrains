package ru.geekbrains.lesson5.human;

import java.util.Random;

public class Human {

    private String name;
    private int age;
    private int hunger = 0;
    private int happy;
    private int knowledge;
    private double money;
    private int hp = 100;

    private final Random random = new Random();

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHunger() {
        this.hunger = random.nextInt(100);
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public void sleep() {
        System.out.println(name + " уснул");
    }

    public void wakeUp() {
        System.out.println(name + " проснулся");
    }

    public void play() {
        happy += random.nextInt(100);
        System.out.println(name + " играет, " + "уровень радости - " + happy);
    }

    public void study() {
        knowledge += random.nextInt(10);
        System.out.println(name + " учится. Уровень знаний: " + knowledge);
    }

    public void cry() {
        System.out.println(name + " плачет");
        if (hunger > 50) {
            System.out.println(name + " хочет есть. Уровень голода: " + hunger);
        }
    }

    public void buy() {
        happy += random.nextInt(100);
        int cost = random.nextInt(10000);
        if (cost > money) {
            System.out.printf(name + " не сделал покупку на %d. Не хватило денег. Баланс: %.2f%n", cost, money);
            return;
        }
        money -= cost;
        System.out.printf(name + " сделал покупку. Потратил: %d. Баланс: %.2f%n", cost, money);
    }

    public void eat() {
        if (age > 18) {
            int cost = random.nextInt(5000);
            if (cost > money) {
                System.out.printf(name + " не поел. Не хватило денег. Баланс: %.2f%n", money);
            } else {
                money -= cost;
                int food = random.nextInt(50);
                hunger = Math.max(hunger - food, 0);
                System.out.printf(name + " ест. Потратил %d. Уровень голода: %d. Баланс: %.2f%n", cost, hunger,  money);
            }
        } else {
            int food = random.nextInt(50);
            hunger = Math.max(hunger - food, 0);
            System.out.println(name + " ест. Уровень голода: " + hunger);
        }

    }

    public void work() {
        double value = random.nextDouble() * 300 * knowledge;
        hunger += random.nextInt(100);
        money += value;
        System.out.printf(name + " работает. Доход: %.2f. Баланс: %.2f%n", value, money);
    }

    public void pension() {
        double value = 100 * age;
        money += value;
        System.out.printf(name + " на пенсии. Доход: %.2f. Баланс: %.2f%n", value, money);
    }

    public boolean heal() {
        hp += random.nextInt(50);
        if (hp < 0) {
            return false;
        }
        System.out.printf(name + " Вылечился. Здоровье: %d%n", hp);
        return true;
    }

    public int ill() {
        int result = 0;
        if (random.nextDouble() > 0.5) {
            result = random.nextInt(50);
            hp -= result;
            System.out.println(name + " Заболел. Здоровье: " + hp);
        }
        return result;
    }
}
