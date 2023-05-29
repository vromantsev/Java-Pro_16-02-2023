package ua.hillel.hw.task25.connection;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private final DataSource dataSource;

    public DatabaseConnectionManager(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DatabaseConnection getConnection() throws SQLException {
        return new DatabaseConnection(this.dataSource.getConnection());
    }
}
