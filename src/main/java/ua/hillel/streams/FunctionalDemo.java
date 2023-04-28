package ua.hillel.streams;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.*;

import static ua.hillel.streams.StreamsDemo.getUsers;

public class FunctionalDemo {
    public static void main(String[] args) {
        List<User> users = getUsers();
        //FunctionalDemo.runConsumer(users.get(0), Printable::printAnotherMessage); // -> users.forEach(user -> System.out.println(user));

        // Consumer, Predicate, Supplier, Function, UnaryOperator &&
        /*users.stream()
                .filter(user -> user.getGender() == User.Gender.MALE)
                .forEach(System.out::println);*/

        /*if (users.get(0).getGender() == User.Gender.MALE) {
            System.out.println(users.get(0));
        }
        for (User u : users) {
            System.out.println("Gender = MALE: " + FunctionalDemo.runPredicate(u, user -> user.getGender() == User.Gender.MALE));
        }

        System.out.println("User: " + FunctionalDemo.runSupplier(() -> new User("Vladyslav", "Romantsev", "vr@mail.com", User.Gender.MALE, 31)));
*/
        System.out.println(FunctionalDemo.convert(users.get(1)));
    }

    private static User convert(User type) {
        UnaryOperator<User> convertFunction = user -> {
            user.setEmail("UPDATED+" + user.getEmail());
            return user;
        };
        return convertFunction.apply(type);
    }

    private static <T> T runSupplier(Supplier<T> supplier) {
        return supplier.get();
    }

    private static boolean runPredicate(User user, Predicate<User> validationPredicate) {
        return validationPredicate.test(user);
    }

    private static void runConsumer(User user, Consumer<User> action) {
        action.accept(user);
    }
}
