package ua.hillel.hw.task25.dao;

import ua.hillel.hw.task25.connection.DatabaseConnection;
import ua.hillel.hw.task25.connection.DatabaseConnectionManager;
import ua.hillel.hw.task25.entity.Lesson;
import ua.hillel.jdbc.exceptions.JdbcOperationException;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class LessonDao implements GenericDao<Lesson, Long> {

    private final DatabaseConnectionManager connectionManager;

    public LessonDao(final DatabaseConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public Lesson create(final Lesson lesson) {
        Objects.requireNonNull(lesson);
        DatabaseConnection connection = null;
        try {
            connection = connectionManager.getConnection();

        } catch (SQLException e) {
            throw new JdbcOperationException(e.getMessage(), e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public boolean delete(final Long id) {
        return false;
    }

    @Override
    public List<Lesson> findAll() {
        return null;
    }

    @Override
    public Lesson findById(final Long id) {
        return null;
    }
}
