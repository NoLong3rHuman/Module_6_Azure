package org.example;

import org.example.db.DatabaseConnection;
import java.sql.Connection;
import java.sql.Statement;

public class CreateDatabase {
    public static void main(String[] args) {

        DatabaseConnection db = new DatabaseConnection();

        String sql = "CREATE DATABASE IF NOT EXISTS persondb";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("✅ Database created successfully!");

        } catch (Exception e) {
            System.out.println("❌ Failed to create database.");
            e.printStackTrace();
        }
    }
}