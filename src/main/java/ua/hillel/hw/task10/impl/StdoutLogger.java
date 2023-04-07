package ua.hillel.hw.task10.impl;

import ua.hillel.hw.task10.AbstractLogger;
import ua.hillel.hw.task10.config.StdoutLoggerConfiguration;
import ua.hillel.hw.task10.enums.LoggingLevel;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class StdoutLogger extends AbstractLogger {

    public StdoutLogger(StdoutLoggerConfiguration configuration) {
        super(configuration);
    }

    @Override
    public void debug(final String message) {
        if (isDebugEnabled()) {
            log(message, LoggingLevel.DEBUG);
        }
    }

    @Override
    public void info(final String message) {
        if (isInfoEnabled()) {
            log(message, LoggingLevel.INFO);
        }
    }

    @Override
    protected void log(final String message, final LoggingLevel loggingLevel) {
        PrintWriter pw = new PrintWriter(System.out, true);
        pw.println(super.formatMessage(message, loggingLevel));
    }
}
