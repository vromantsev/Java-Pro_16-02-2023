package ua.hillel.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StreamsDemo {

    public static void main(String[] args) {
        var users = getUsers();

        // (args) -> lambda body
        List<User> gmailUsers = filterUsersByGmail(users);

        users.stream()
                .map((User user) -> user.getEmail())
                .filter(email -> email != null)
                .peek(System.out::println)
                .forEach(System.out::println);
        // operations that can return some result - terminal operations
        // operations that can NOT return some result - intermediate operations
    }

    private static List<User> filterUsersByGmail(List<User> users) {
        return users.stream()
                .filter(user -> user.getEmail().split("@")[1].equals("gmail.com")) // if (user.getEmail().split("@")[1].equals("gmail.com")) { // some operation }
                .collect(Collectors.toList()); // mutable list
                //.toList(); // immutable list
    }

    private static List<User> getUsers() {
        return new ArrayList<>(
                Arrays.asList(
                        new User("John", "Doe", "jd@gmail.com", User.Gender.MALE, 30),
                        new User("Sam", "Serious", "ss@mail.com", User.Gender.MALE, 35),
                        new User("Artur", "Semenov", "as@test.com", User.Gender.MALE, 50),
                        new User("Linda", "Crowford", "lc@new.com", User.Gender.FEMALE, 60),
                        new User("Sara", "Buckman", "sb@gmail.com", User.Gender.FEMALE, 20)
                )
        );
    }

    private static Integer findMaxUsingLoops(List<Integer> ints) {
        Objects.requireNonNull(ints);
        Integer max = -1;
        for (Integer value : ints) {
            if (value != null && max.compareTo(value) < 0) {
                max = value;
            }
        }
        return max;
    }

    // Method reference ->
    // 1. static method reference,
    // 2. constructor reference,
    // 3. objects instance method reference
    // 4. method reference of an objects of particular type
    private static Integer findMax(List<Integer> ints) {
        return ints.stream()
                .max(Integer::compareTo)
                .orElseThrow();
    }
}
