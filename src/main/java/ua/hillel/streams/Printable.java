package ua.hillel.streams;

@FunctionalInterface // <- best practice - when you need custom functional interface, you should put @FunctionalInterface on a class level
public interface Printable {

    void printUserInfo(User user);

    static void printAnotherMessage(User user) {
        System.out.println(setLogLevelToMessage(user.toString(), "INFO"));
    }

    static String setLogLevelToMessage(String message, String logLevel) {
        return "[" + logLevel + "] " + message;
    }
}
