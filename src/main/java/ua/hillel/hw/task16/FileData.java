package ua.hillel.hw.task16;

import java.util.Objects;

public class FileData {

    private String fileName;
    private String pathToFile;
    private long bytes;

    public FileData(String fileName, String pathToFile, long bytes) {
        this.fileName = fileName;
        this.pathToFile = pathToFile;
        this.bytes = bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    public void setPathToFile(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FileData fileData = (FileData) o;

        if (bytes != fileData.bytes) return false;
        if (!Objects.equals(fileName, fileData.fileName)) return false;
        return Objects.equals(pathToFile, fileData.pathToFile);
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (pathToFile != null ? pathToFile.hashCode() : 0);
        result = 31 * result + (int) (bytes ^ (bytes >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "FileData{" +
                "fileName='" + fileName + '\'' +
                ", pathToFile='" + pathToFile + '\'' +
                ", bytes=" + bytes +
                '}';
    }
}
