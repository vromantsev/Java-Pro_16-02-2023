package ua.hillel.hw.task10;

import ua.hillel.hw.task10.config.Configuration;
import ua.hillel.hw.task10.enums.LoggingLevel;

import java.util.Objects;

public abstract class AbstractLogger implements Logger {

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

}
