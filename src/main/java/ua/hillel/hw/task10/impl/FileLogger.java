package ua.hillel.hw.task10.impl;

import ua.hillel.hw.task10.AbstractLogger;
import ua.hillel.hw.task10.config.FileLoggerConfiguration;
import ua.hillel.hw.task10.enums.LoggingLevel;
import ua.hillel.hw.task10.loader.FileLoggerConfigurationLoader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends AbstractLogger {

    static final String CONFIG_FILE_PATH = "logger-cfg.properties";
    private static final String LOG_PREFIX = "logs-";
    private static final String LOG_FORMAT = ".log";

    public FileLogger() {
        this(FileLoggerConfigurationLoader.load(CONFIG_FILE_PATH));
    }

    public FileLogger(final FileLoggerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void debug(String message) {
        if (isDebugEnabled()) {
            log(message, LoggingLevel.DEBUG);
        }
    }

    @Override
    public void info(String message) {
        if (isInfoEnabled()) {
            log(message, LoggingLevel.INFO);
        }
    }

    @Override
    protected void log(String message, LoggingLevel loggingLevel) {
        var fileLoggerConfiguration = (FileLoggerConfiguration) super.configuration;
        var logFile = fileLoggerConfiguration.getLogFile();
        boolean maxSizeExceeded = logFile.length() > fileLoggerConfiguration.getMaxFileSizeBytes();
        String alignedMessage = formatMessage(message, loggingLevel) + " " + "\n";
        if (maxSizeExceeded) {
            String newFileName = generateLogFileName();
            writeToFile(alignedMessage, new File(newFileName), false);
        } else {
            writeToFile(alignedMessage, logFile, true);
        }
    }

    private String generateLogFileName() {
        return LOG_PREFIX + DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN).format(LocalDateTime.now()) + LOG_FORMAT;
    }

    private void writeToFile(String message, File logFile, boolean append) {
        try (var out = new FileOutputStream(logFile, append)) {
            out.write(message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
