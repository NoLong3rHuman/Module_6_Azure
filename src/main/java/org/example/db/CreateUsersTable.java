package org.example.db;


import org.example.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.Statement;

public class CreateUsersTable {
    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();

        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) NOT NULL UNIQUE,
                    phone VARCHAR(20),
                    address VARCHAR(255),
                    password VARCHAR(100) NOT NULL,
                    image_path VARCHAR(255)
                )
                """;

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("✅ Users table created successfully!");

        } catch (Exception e) {
            System.out.println("❌ Failed to create users table.");
            e.printStackTrace();
        }
    }
}
