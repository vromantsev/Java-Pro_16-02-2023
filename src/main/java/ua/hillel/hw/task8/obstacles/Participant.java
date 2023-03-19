package ua.hillel.hw.task8.obstacles;

public class Participant {

    private String name;
    private int maxJumpHeight;
    private int maxRunDistance;

    public Participant(String name, int maxJumpHeight, int maxRunDistance) {
        this.name = name;
        this.maxJumpHeight = maxJumpHeight;
        this.maxRunDistance = maxRunDistance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxJumpHeight() {
        return maxJumpHeight;
    }

    public void setMaxJumpHeight(int maxJumpHeight) {
        this.maxJumpHeight = maxJumpHeight;
    }

    public int getMaxRunDistance() {
        return maxRunDistance;
    }

    public void setMaxRunDistance(int maxRunDistance) {
        this.maxRunDistance = maxRunDistance;
    }

    public void run() {
        System.out.printf("Participant %s runs.%n", this.name);
    }

    public void jump() {
        System.out.printf("Participant %s jumps.%n", this.name);
    }
}
