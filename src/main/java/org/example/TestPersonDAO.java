package org.example;

import org.example.db.PersonDAO;

import java.util.List;

public class TestPersonDAO {
    public static void main(String[] args) {
        PersonDAO dao = new PersonDAO();

        // 1. INSERT
        Person person = new Person(
                "Daniel",
                "daniel@test.com",
                "555-1234",
                "Hicksville",
                "password123",
                "profile.png"
        );

        boolean inserted = dao.insertPerson(person);
        System.out.println("Insert worked: " + inserted);

        // 2. READ
        List<Person> people = dao.getAllPeople();
        System.out.println("\nAll users after insert:");
        for (Person p : people) {
            System.out.println(
                    p.getId() + " | " +
                            p.getName() + " | " +
                            p.getEmail() + " | " +
                            p.getPhone()
            );
        }

        // 3. UPDATE first person in list (if any)
        if (!people.isEmpty()) {
            Person first = people.get(0);
            first.setName("Daniel Updated");
            first.setPhone("999-9999");

            boolean updated = dao.updatePerson(first);
            System.out.println("\nUpdate worked: " + updated);
        }

        // 4. READ again
        people = dao.getAllPeople();
        System.out.println("\nAll users after update:");
        for (Person p : people) {
            System.out.println(
                    p.getId() + " | " +
                            p.getName() + " | " +
                            p.getEmail() + " | " +
                            p.getPhone()
            );
        }

        // 5. DELETE first person in list (if any)
        if (!people.isEmpty()) {
            int idToDelete = people.get(0).getId();
            boolean deleted = dao.deletePerson(idToDelete);
            System.out.println("\nDelete worked: " + deleted);
        }

        // 6. FINAL READ
        people = dao.getAllPeople();
        System.out.println("\nAll users after delete:");
        for (Person p : people) {
            System.out.println(
                    p.getId() + " | " +
                            p.getName() + " | " +
                            p.getEmail() + " | " +
                            p.getPhone()
            );
        }
    }
}