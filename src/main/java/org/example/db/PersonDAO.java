package org.example.db;

import org.example.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO {

    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public boolean insertPerson(Person person) {
        String sql = "INSERT INTO users (name, email, phone, address, password, image_path) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setString(3, person.getPhone());
            pstmt.setString(4, person.getAddress());
            pstmt.setString(5, person.getPassword());
            pstmt.setString(6, person.getImagePath());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Person> getAllPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Person person = new Person(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("password"),
                        rs.getString("image_path")
                );
                people.add(person);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return people;
    }
    public boolean updatePerson(Person person) {
        String sql = "UPDATE users SET name = ?, email = ?, phone = ?, address = ?, password = ?, image_path = ? WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, person.getName());
            pstmt.setString(2, person.getEmail());
            pstmt.setString(3, person.getPhone());
            pstmt.setString(4, person.getAddress());
            pstmt.setString(5, person.getPassword());
            pstmt.setString(6, person.getImagePath());
            pstmt.setInt(7, person.getId());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deletePerson(int id) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}