package ua.hillel.hw.task10;

import ua.hillel.hw.task10.config.FileLoggerConfiguration;
import ua.hillel.hw.task10.config.StdoutLoggerConfiguration;
import ua.hillel.hw.task10.enums.LoggingLevel;
import ua.hillel.hw.task10.impl.FileLogger;
import ua.hillel.hw.task10.impl.StdoutLogger;

import java.io.File;

public class LoggerDemo {
    public static void main(String[] args) {
        FileLoggerConfiguration configuration = new FileLoggerConfiguration(
                new File("log1.log"), LoggingLevel.DEBUG, 1024, "dd-MM-yyyy HH:mm:ss"
        );
        Logger fileLogger = new FileLogger(configuration);
        fileLogger.debug("file debug message");
        fileLogger.info("file info message");

        Logger stdoutLogger = new StdoutLogger(new StdoutLoggerConfiguration(LoggingLevel.DEBUG, "dd-MM-yyyy HH:mm:ss"));
        stdoutLogger.info("test info");
        stdoutLogger.debug("test debug");
    }
}
