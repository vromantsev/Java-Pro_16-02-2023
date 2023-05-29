package ua.hillel.hw.task25.entity;

import java.util.Objects;

public class Lesson {

    private Long id;
    private String name;
    private Homework homework;

    public Lesson() {
    }

    public Lesson(Long id, String name, Homework homework) {
        this.id = id;
        this.name = name;
        this.homework = homework;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lesson lesson = (Lesson) o;

        if (!Objects.equals(id, lesson.id)) return false;
        if (!Objects.equals(name, lesson.name)) return false;
        return Objects.equals(homework, lesson.homework);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (homework != null ? homework.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", homework=" + homework +
                '}';
    }
}
