package ru.geekbrains2.lesson1.participant;

public class Robot implements Participant {
    private final int maxRun;
    private final int maxJump;

    public Robot(int maxRun, int maxJump) {
        this.maxRun = maxRun;
        this.maxJump = maxJump;
    }

    public int getMaxRun() {
        return maxRun;
    }

    public int getMaxJump() {
        return maxJump;
    }

    @Override
    public boolean run(int distance) {
        if (distance > maxRun) {
            return false;
        }
        System.out.println("Robot run");
        return true;
    }

    @Override
    public boolean jump(int distance) {
        if (distance > maxJump) {
            return false;
        }
        System.out.println("Robot jump");
        return true;
    }
}
