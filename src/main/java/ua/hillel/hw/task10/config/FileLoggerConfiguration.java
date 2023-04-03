package ua.hillel.hw.task10.config;

import ua.hillel.hw.task10.enums.LoggingLevel;

import java.io.File;
import java.util.Objects;

public class FileLoggerConfiguration implements Configuration {

    private File logFile;
    private LoggingLevel loggingLevel;
    private int maxFileSizeBytes;
    private String format;

    public FileLoggerConfiguration(File logFile, LoggingLevel loggingLevel, int maxFileSizeBytes, String format) {
        this.logFile = Objects.requireNonNull(logFile);
        this.loggingLevel = Objects.requireNonNull(loggingLevel);
        this.maxFileSizeBytes = checkMaxFileSize(maxFileSizeBytes);
        this.format = Objects.requireNonNull(format);
    }

    public File getLogFile() {
        return logFile;
    }

    public void setLogFile(File logFile) {
        this.logFile = logFile;
    }

    @Override
    public LoggingLevel getLoggingLevel() {
        return loggingLevel;
    }

    @Override
    public void setLoggingLevel(LoggingLevel loggingLevel) {
        this.loggingLevel = loggingLevel;
    }

    public int getMaxFileSizeBytes() {
        return maxFileSizeBytes;
    }

    public void setMaxFileSizeBytes(int maxFileSizeBytes) {
        this.maxFileSizeBytes = maxFileSizeBytes;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public void setFormat(String format) {
        this.format = format;
    }

    private int checkMaxFileSize(int maxFileSizeBytes) {
        if (maxFileSizeBytes < 1) {
            throw new IllegalArgumentException("Invalid size of a file! Size should be at least 1 byte!");
        }
        return maxFileSizeBytes;
    }
}
