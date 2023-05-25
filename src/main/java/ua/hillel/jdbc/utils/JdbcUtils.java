package ua.hillel.jdbc.utils;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.Set;

public class JdbcUtils {

    private static final Set<String> TABLES = Set.of("user", "order", "product");

    private static final String DB_PROPS = "db.properties";
    private static final String DB_URL = "db.url";
    private static final String DB_USERNAME = "db.username";
    private static final String DB_PASSWORD = "db.password";

    private static Properties loadProperties() {
        try (InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream(DB_PROPS)) {
            Properties dbProperties = new Properties();
            dbProperties.load(is);
            return dbProperties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource initDataSource() {
        Properties props = loadProperties();
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(props.getProperty(DB_URL));
        dataSource.setUser(props.getProperty(DB_USERNAME));
        dataSource.setPassword(props.getProperty(DB_PASSWORD));
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        Properties props = loadProperties();
        return DriverManager.getConnection(
                props.getProperty(DB_URL), props.getProperty(DB_USERNAME), props.getProperty(DB_PASSWORD)
        );
    }

    public static void main(String[] args) {
        var dataSource = initDataSource();
        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet tables = databaseMetaData.getTables(null, null, null, null);
            while (tables.next()) {
                if (TABLES.contains(tables.getString(3))) {
                    System.out.println("Table = " + tables.getString(3));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
