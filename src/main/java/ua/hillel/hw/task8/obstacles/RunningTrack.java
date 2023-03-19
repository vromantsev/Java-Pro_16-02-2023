package ua.hillel.hw.task8.obstacles;

import java.util.Objects;

public class RunningTrack extends Obstacle {

    private int distance;

    public RunningTrack(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public boolean overcome(Participant participant) {
        Objects.requireNonNull(participant, "Parameter [participant] must not be null!");
        return participant.getMaxRunDistance() >= this.distance;
    }
}
