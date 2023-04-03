package ua.hillel.hw.task10;

import ua.hillel.hw.task10.config.FileLoggerConfiguration;
import ua.hillel.hw.task10.enums.LoggingLevel;
import ua.hillel.hw.task10.impl.FileLogger;

import java.io.File;

public class LoggerDemo {
    public static void main(String[] args) {
        FileLoggerConfiguration configuration = new FileLoggerConfiguration(
                new File("log1.log"), LoggingLevel.DEBUG, 1024, "dd-MM-yyyy HH:mm:ss"
        );
        Logger logger = new FileLogger(configuration);
        logger.info("test info");
        logger.debug("test debug");
    }
}
