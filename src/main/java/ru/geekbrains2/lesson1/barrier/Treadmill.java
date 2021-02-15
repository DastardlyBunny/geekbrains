package ru.geekbrains2.lesson1.barrier;

import ru.geekbrains2.lesson1.participant.Participant;

public class Treadmill implements Barrier {

    private final int length;

    public Treadmill(int length) {
        this.length = length;
    }

    @Override
    public boolean action(Participant participant) {
        if (length > participant.getMaxRun()) {
            return false;
        }
        participant.run(length);
        return true;
    }
}