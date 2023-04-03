package ua.hillel.hw.task10.config;

import ua.hillel.hw.task10.enums.LoggingLevel;

import java.util.Objects;

public class StdoutLoggerConfiguration implements Configuration {

    private LoggingLevel loggingLevel;
    private String format;

    public StdoutLoggerConfiguration(LoggingLevel loggingLevel, String format) {
        this.loggingLevel = Objects.requireNonNull(loggingLevel);
        this.format = Objects.requireNonNull(format);
    }

    @Override
    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    @Override
    public void setLoggingLevel(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }
}
