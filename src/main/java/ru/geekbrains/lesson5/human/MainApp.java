package ru.geekbrains.lesson5.human;

public class MainApp {

    public static void main(String[] args) {

        Human human = new Human("Ivan");

        for (int i = 0; i < 100; i++) {
            int age = human.getAge();

            human.setHappy(0);
            human.wakeUp();
            human.setHunger();

            if (human.getAge() < 1) {
                human.cry();
            }

            human.eat();

            if (age > 4 && age < 30) {
                human.study();
            }

            if (age > 18) {
                human.buy();
                if (age < 50) {
                    human.work();
                } else {
                    human.pension();
                }
            }

            int ill = human.ill();
            if (ill > 0) {
                if (!human.heal()) {
                    System.out.printf(human.getName() + " умер. Неизлечимая болезнь. Возраст: %d.", age);
                    return;
                }
            }

            while (human.getHappy() < 100 && age < 20) {
                human.play();
            }

            human.eat();

            human.sleep();
            human.setAge(age + 1);
            System.out.println(human.getName() + " позвзрослел. Возраст: " + age);
            System.out.println();
        }
    }
}
