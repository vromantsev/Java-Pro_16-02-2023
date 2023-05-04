package ua.hillel.hw.task16;

import java.util.*;

public class FileNavigator {

    private final Map<String, FileData> filePathToFileDataMap = new HashMap<>();

    public void add(final String filePath, final FileData fileData) {
        Objects.requireNonNull(filePath);
        Objects.requireNonNull(fileData);
        if (filePath.equals(fileData.getPathToFile())) {
            throw new IllegalArgumentException("Paths to files are different: %s != %s".formatted(fileData.getPathToFile(), fileData.getPathToFile()));
        }
        this.filePathToFileDataMap.put(fileData.getPathToFile(), fileData);
    }

    public List<FileData> find(final String pathToFile) {
        Objects.requireNonNull(pathToFile);
        return this.filePathToFileDataMap.entrySet().stream()
                .filter(e -> e.getKey().equals(pathToFile))
                .map(Map.Entry::getValue)
                .toList();
    }

    public List<FileData> filterBySize(final long size) {
        return this.filePathToFileDataMap.values()
                .stream()
                .filter(fd -> fd.getBytes() <= size)
                .toList();
    }

    public boolean remove(final String filePath) {
        Objects.requireNonNull(filePath);
        if (this.filePathToFileDataMap.containsKey(filePath)) {
            this.filePathToFileDataMap.remove(filePath);
            return true;
        }
        return false;
    }

    public List<FileData> sortBySize() {
        return this.filePathToFileDataMap.values().stream()
                .sorted(Comparator.comparing(FileData::getBytes))
                .toList();
    }
}
