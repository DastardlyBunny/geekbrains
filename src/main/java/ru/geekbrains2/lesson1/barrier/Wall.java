package ru.geekbrains2.lesson1.barrier;

import ru.geekbrains2.lesson1.participant.Participant;

public class Wall implements Barrier {

    private final int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public boolean action(Participant participant) {
        return participant.jump(height);
    }
}
