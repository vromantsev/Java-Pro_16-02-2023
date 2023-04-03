package ua.hillel.hw.task10.impl;

import ua.hillel.hw.task10.AbstractLogger;
import ua.hillel.hw.task10.config.StdoutLoggerConfiguration;

public class StdoutLogger extends AbstractLogger {

    public StdoutLogger(StdoutLoggerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void debug(String message) {
        if (isDebugEnabled()) {

        }
    }

    @Override
    public void info(String message) {
        if (isInfoEnabled()) {

        }
    }
}
