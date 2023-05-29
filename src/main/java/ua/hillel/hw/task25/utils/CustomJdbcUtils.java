package ua.hillel.hw.task25.utils;

import org.postgresql.ds.PGSimpleDataSource;
import ua.hillel.hw.task25.connection.DatabaseConnection;
import ua.hillel.jdbc.exceptions.JdbcOperationException;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public final class CustomJdbcUtils {

    private static final Logger LOGGER = Logger.getLogger(DatabaseConnection.class.getName());

    private static final String DB_PROPS = "db.properties";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private CustomJdbcUtils() {
    }

    public static DataSource initDataSource() {
        Properties props = loadProperties();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(props.getProperty(DB_URL));
        dataSource.setUser(props.getProperty(DB_USERNAME));
        dataSource.setPassword(props.getProperty(DB_PASSWORD));
        return dataSource;
    }

    public static void initSqlScript(final String script, final DataSource dataSource) {
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            String sql = String.join("\n", Files.readAllLines(Paths.get(script)));
            int scriptsExecuted = statement.executeUpdate(sql);
            if (scriptsExecuted <= 0) {
                LOGGER.severe("No scripts have been deployed against the database.");
            }
        } catch (SQLException e) {
            throw new JdbcOperationException(e.getMessage(), e);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Properties loadProperties() {
        try (InputStream is = DatabaseConnection.class.getClassLoader().getResourceAsStream(DB_PROPS)) {
            Properties dbProperties = new Properties();
            dbProperties.load(is);
            return dbProperties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
