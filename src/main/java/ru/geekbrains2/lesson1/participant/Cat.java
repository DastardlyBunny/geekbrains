package ru.geekbrains2.lesson1.participant;

public class Cat implements Participant {
    private final int maxRun;
    private final int maxJump;

    public Cat(int maxRun, int maxJump) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    @Override
    public boolean run(int distance) {
        if (distance > maxRun) {
            return false;
        }
        System.out.println("Cat run");
        return true;
    }

    @Override
    public boolean jump(int distance) {
        if (distance > maxJump) {
            return false;
        }
        System.out.println("Cat jump");
        return true;
    }


}
