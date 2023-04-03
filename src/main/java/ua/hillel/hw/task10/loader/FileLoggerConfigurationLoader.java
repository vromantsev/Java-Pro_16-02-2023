package ua.hillel.hw.task10.loader;

import ua.hillel.hw.task10.config.FileLoggerConfiguration;
import ua.hillel.hw.task10.enums.LoggingLevel;

import java.io.*;
import java.util.Objects;
import java.util.Properties;

public final class FileLoggerConfigurationLoader {

    private static final String FILE_NAME = "file.name";
    private static final String LOGGING_LEVEL = "logging.level";
    private static final String MAX_FILE_SIZE = "max.file.size";
    private static final String LOG_FORMAT = "log.format";

    private FileLoggerConfigurationLoader() {}

    public static FileLoggerConfiguration load(final String configFilePath) {
        try {
            Properties props = new Properties();
            props.load(FileLoggerConfigurationLoader.class.getClassLoader().getResourceAsStream(Objects.requireNonNull(configFilePath)));
            return new FileLoggerConfiguration(
                    new File(props.getProperty(FILE_NAME)),
                    Enum.valueOf(LoggingLevel.class, props.getProperty(LOGGING_LEVEL)),
                    Integer.parseInt(props.getProperty(MAX_FILE_SIZE)),
                    props.getProperty(LOG_FORMAT)
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
