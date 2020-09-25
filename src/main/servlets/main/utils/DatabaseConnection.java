package main.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// This class can be used to initialize the database connection
public class DatabaseConnection implements IDatabaseManager {
    private Connection connection;

    @Override
    public ConnectionInfo acquire() {
        try {
            if (connection == null) {
                connection = initializeDatabase();
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return new ConnectionInfo(1, connection);
    }

    @Override
    public void release(ConnectionInfo connectionInfo) {

    }

    private static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException {

        // Initialize all the information regarding
        // Database Connection
        String ip = "localhost";
        String port = "3333";

        // Database name to access
        String dbName = "onlinetest";
        String dbUsername = "andre";
        String dbPassword = "";

        String url = "jdbc:mariadb://" + ip + ":" + port + "/" + dbName;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            return DriverManager.getConnection(url, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
