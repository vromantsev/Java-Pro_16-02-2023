package ua.hillel.hw.task10.config;

import ua.hillel.hw.task10.enums.LoggingLevel;

public interface Configuration {

    LoggingLevel getLoggingLevel();

    void setLoggingLevel(LoggingLevel loggingLevel);

    String getFormat();

    void setFormat(String format);

}
