package ua.hillel.hw.task10;

import ua.hillel.hw.task10.config.Configuration;
import ua.hillel.hw.task10.enums.LoggingLevel;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class AbstractLogger implements Logger {

    protected static final String DEFAULT_DATE_PATTERN = "dd-MM-yyyy HH:mm:ss";

    protected final Configuration configuration;

    public AbstractLogger(final Configuration configuration) {
        this.configuration = Objects.requireNonNull(configuration);
    }

    @Override
    public boolean isInfoEnabled() {
        return this.configuration.getLoggingLevel() == LoggingLevel.INFO || this.configuration.getLoggingLevel() == LoggingLevel.DEBUG;
    }

    @Override
    public boolean isDebugEnabled() {
        return this.configuration.getLoggingLevel() == LoggingLevel.DEBUG;
    }

    public abstract void debug(final String message);

    public abstract void info(final String message);

    protected abstract void log(final String message, final LoggingLevel loggingLevel);

    public String formatMessage(final String message, final LoggingLevel loggingLevel) {
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
