package ua.hillel.hw.task8.obstacles;

import java.util.Objects;

public class Competition {

    private Participant[] participants;
    private Obstacle[] obstacles;

    public Competition(Participant[] participants, Obstacle[] obstacles) {
        this.participants = Objects.requireNonNull(participants);
        this.obstacles = Objects.requireNonNull(obstacles);
    }

    public CompetitionResult start() {
        StringBuilder successes = new StringBuilder();
        StringBuilder failures = new StringBuilder();
        for (Participant participant : participants) {
            for (Obstacle obstacle : obstacles) {
                if (obstacle.overcome(participant)) {
                    successes.append("Participant ")
                            .append(participant.getName())
                            .append(" overcame a ")
                            .append(obstacle.getClass().getSimpleName())
                            .append(" ");
                    appendObstacleDetails(successes, failures, obstacle, true);
                } else {
                    // append failure
                    failures.append("Participant ")
                            .append(participant.getName())
                            .append(" failed to overcome ");
                    appendObstacleDetails(successes, failures, obstacle, false);
                    // exclude participant from competition
                    break;
                }
            }
        }
        return CompetitionResult.of(successes.toString(), failures.toString());
    }

    private void appendObstacleDetails(StringBuilder successes,
                                       StringBuilder failures,
                                       Obstacle obstacle,
                                       boolean success) {
        if (success) {
            if (obstacle instanceof Wall wall) {
                successes.append(wall.getHeight()).append(" meters height");
            } else if (obstacle instanceof RunningTrack runningTrack) {
                successes.append(runningTrack.getDistance()).append(" meters long");
            }
            successes.append("\n");
        } else {
            if (obstacle instanceof Wall wall) {
                failures.append("a wall ").append(wall.getHeight()).append(" meters height");
            } else if (obstacle instanceof RunningTrack runningTrack) {
                failures.append("a running track ").append(runningTrack.getDistance()).append(" meters long");
            }
            failures.append("\n");
        }
    }

    static class CompetitionResult {

        private String successes;
        private String failures;

        private CompetitionResult(String successes, String failures) {
            this.successes = successes;
            this.failures = failures;
        }

        public static CompetitionResult of(String successes, String failures) {
            return new CompetitionResult(successes, failures);
        }

        @Override
        public String toString() {
            return "Competition result: \n" + successes + "\n" + failures;
        }
    }
}
