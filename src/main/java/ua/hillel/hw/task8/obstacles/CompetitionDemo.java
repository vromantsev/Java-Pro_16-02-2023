package ua.hillel.hw.task8.obstacles;

public class CompetitionDemo {
    public static void main(String[] args) {
        Participant[] participants = {
                new Cat("Luna", 2, 200),
                new Robot("C3PO", 1, 500),
                new Human("Vlad", 1, 1000)
        };
        Obstacle[] obstacles = {
                new Wall(2), new RunningTrack(800)
        };
        Competition competition = new Competition(participants, obstacles);
        var result = competition.start();
        System.out.println(result.toString());
    }
}
