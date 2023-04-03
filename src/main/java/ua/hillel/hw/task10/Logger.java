package ua.hillel.hw.task10;

public interface Logger {

    void debug(String message);

    void info(String message);

    boolean isInfoEnabled();

    boolean isDebugEnabled();

}
