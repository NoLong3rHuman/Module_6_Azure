package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:mysql://skyl4.mysql.database.azure.com:3306/persondb" +
                    "?useSSL=true&requireSSL=true&serverTimezone=UTC" +
                    "&connectTimeout=10000&socketTimeout=10000";

    private static final String USER = "pined20";
    private static final String PASSWORD = "Skyluvsme24";

    public Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC driver not found.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}