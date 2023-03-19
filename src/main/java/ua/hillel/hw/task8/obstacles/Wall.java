package ua.hillel.hw.task8.obstacles;

import java.util.Objects;

public class Wall extends Obstacle {

    private int height;

    public Wall(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean overcome(Participant participant) {
        Objects.requireNonNull(participant, "Parameter [participant] must not be null!");
        return participant.getMaxJumpHeight() >= this.height;
    }
}
