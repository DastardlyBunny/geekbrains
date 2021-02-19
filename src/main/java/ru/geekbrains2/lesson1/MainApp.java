package ru.geekbrains2.lesson1;

import ru.geekbrains2.lesson1.barrier.*;
import ru.geekbrains2.lesson1.participant.*;

public class MainApp {
    public static void main(String[] args) {
        Participant[] participant = new Participant[] {
                new Cat(10, 10),
                new Man(10, 30),
                new Robot(5, 10)
        };

        Barrier[] barrier = new Barrier[] { new Treadmill(10), new Wall(20) };

        for (Participant p : participant) {
            for (Barrier b : barrier) {
                if (!b.action(p)) {
                    break;
                }
            }
        }
    }
}
