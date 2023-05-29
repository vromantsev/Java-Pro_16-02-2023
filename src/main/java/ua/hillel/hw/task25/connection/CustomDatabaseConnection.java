package ua.hillel.hw.task25.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface CustomDatabaseConnection {

    Connection getConnection() throws SQLException;

    void close();

}
