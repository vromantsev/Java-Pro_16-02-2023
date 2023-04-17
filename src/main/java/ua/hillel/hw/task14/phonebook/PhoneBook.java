package ua.hillel.hw.task14.phonebook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class PhoneBook {

    private final Map<String, Record> records = new HashMap<>();

    public PhoneBook() {
    }

    public void add(final Record record) {
        this.records.put(record.phone(), record);
    }

    public Record find(final String name) {
        Objects.requireNonNull(name);
        return this.records.values().stream()
                .filter(r -> r.name().equals(name))
                .findAny()
                .orElse(null);
    }

    public List<Record> findAll(final String name) {
        Objects.requireNonNull(name);
        return records.values().stream()
                .filter(r -> r.name().equals(name))
                .toList();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PhoneBook) obj;
        return Objects.equals(this.records, that.records);
    }

    @Override
    public int hashCode() {
        return Objects.hash(records);
    }

    @Override
    public String toString() {
        return "PhoneBook[" +
                "records=" + records + ']';
    }

}
