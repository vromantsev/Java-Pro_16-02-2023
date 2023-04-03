package ua.hillel.hw.task10.impl;

import ua.hillel.hw.task10.AbstractLogger;
import ua.hillel.hw.task10.config.FileLoggerConfiguration;
import ua.hillel.hw.task10.enums.LoggingLevel;
import ua.hillel.hw.task10.loader.FileLoggerConfigurationLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends AbstractLogger {

    static final String CONFIG_FILE_PATH = "logger-cfg.properties";
    private static final String LOG_PREFIX = "logs-";
    private static final String LOG_FORMAT = ".log";
    private static final String DEFAULT_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";

    public FileLogger() {
        this(FileLoggerConfigurationLoader.load(CONFIG_FILE_PATH));
    }

    public FileLogger(final FileLoggerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void debug(String message) {
        if (isDebugEnabled()) {
            writeToFile(message, LoggingLevel.DEBUG);
        }
    }

    @Override
    public void info(String message) {
        if (isInfoEnabled()) {
            writeToFile(message, LoggingLevel.INFO);
        }
    }

    private void writeToFile(String message, LoggingLevel loggingLevel) {
        var fileLoggerConfiguration = (FileLoggerConfiguration) super.configuration;
        var logFile = fileLoggerConfiguration.getLogFile();
        boolean maxSizeExceeded = logFile.length() > fileLoggerConfiguration.getMaxFileSizeBytes();
        String alignedMessage = formatMessage(message, loggingLevel) + " " + "\n";
        if (maxSizeExceeded) {
            String newFileName = generateLogFileName();
            doWrite(alignedMessage, new File(newFileName), false);
        } else {
            doWrite(alignedMessage, logFile, true);
        }
    }

    private String generateLogFileName() {
        return LOG_PREFIX + DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN).format(LocalDateTime.now()) + LOG_FORMAT;
    }

    private void doWrite(String message, File logFile, boolean append) {
        try (var out = new FileOutputStream(logFile, append)) {
            out.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String formatMessage(final String message, final LoggingLevel loggingLevel) {
        StringBuilder sb = new StringBuilder();
        var format = configuration.getFormat();
        var defaults = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN).format(LocalDateTime.now()) + loggingLevel + " " + message;
        try {
            if (format == null || format.isEmpty()) {
                return defaults;
            }
            var dateTimeFormatter = DateTimeFormatter.ofPattern(configuration.getFormat());
            return sb
                    .append(dateTimeFormatter.format(LocalDateTime.now()))
                    .append(" ")
                    .append(loggingLevel)
                    .append(" ")
                    .append(message)
                    .toString();
        } catch (DateTimeException dateTimeException) {
            return defaults;
        }
    }
}
