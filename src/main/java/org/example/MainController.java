package org.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.db.PersonDAO;

import java.io.File;
import java.util.List;

public class MainController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField addressField;
    @FXML private PasswordField passwordField;

    @FXML private TableView<Person> personTable;
    @FXML private TableColumn<Person, Integer> idColumn;
    @FXML private TableColumn<Person, String> nameColumn;
    @FXML private TableColumn<Person, String> emailColumn;
    @FXML private TableColumn<Person, String> phoneColumn;
    @FXML private TableColumn<Person, String> addressColumn;

    private final PersonDAO personDAO = new PersonDAO();
    private String selectedImagePath;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadPeople();

        personTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, selectedPerson) -> {
            if (selectedPerson != null) {
                fillForm(selectedPerson);
            }
        });
    }

    private void loadPeople() {
        List<Person> people = personDAO.getAllPeople();
        ObservableList<Person> observablePeople = FXCollections.observableArrayList(people);
        personTable.setItems(observablePeople);
    }

    private void fillForm(Person person) {
        nameField.setText(person.getName());
        emailField.setText(person.getEmail());
        phoneField.setText(person.getPhone());
        addressField.setText(person.getAddress());
        passwordField.setText(person.getPassword());
        selectedImagePath = person.getImagePath();
    }

    @FXML
    private void handleAdd() {
        if (!validateInput()) return;

        Person person = new Person(
                nameField.getText(),
                emailField.getText(),
                phoneField.getText(),
                addressField.getText(),
                passwordField.getText(),
                selectedImagePath
        );

        boolean success = personDAO.insertPerson(person);

        if (success) {
            showAlert("Success", "User added successfully.");
            loadPeople();
            clearForm();
        } else {
            showAlert("Error", "Failed to add user.");
        }
    }

    @FXML
    private void handleUpdate() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();

        if (selectedPerson == null) {
            showAlert("Warning", "Please select a user to update.");
            return;
        }

        if (!validateInput()) return;

        selectedPerson.setName(nameField.getText());
        selectedPerson.setEmail(emailField.getText());
        selectedPerson.setPhone(phoneField.getText());
        selectedPerson.setAddress(addressField.getText());
        selectedPerson.setPassword(passwordField.getText());
        selectedPerson.setImagePath(selectedImagePath);

        boolean success = personDAO.updatePerson(selectedPerson);

        if (success) {
            showAlert("Success", "User updated successfully.");
            loadPeople();
            clearForm();
        } else {
            showAlert("Error", "Failed to update user.");
        }
    }

    @FXML
    private void handleDelete() {
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();

        if (selectedPerson == null) {
            showAlert("Warning", "Please select a user to delete.");
            return;
        }

        boolean success = personDAO.deletePerson(selectedPerson.getId());

        if (success) {
            showAlert("Success", "User deleted successfully.");
            loadPeople();
            clearForm();
        } else {
            showAlert("Error", "Failed to delete user.");
        }
    }

    @FXML
    private void handleClear() {
        clearForm();
    }

    @FXML
    private void handleUploadImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Image");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            selectedImagePath = file.toURI().toString();
            showAlert("Image Selected", "Profile image selected successfully.");
        }
    }

    private void clearForm() {
        nameField.clear();
        emailField.clear();
        phoneField.clear();
        addressField.clear();
        passwordField.clear();
        selectedImagePath = null;
        personTable.getSelectionModel().clearSelection();
    }

    private boolean validateInput() {
        if (nameField.getText().isBlank()) {
            showAlert("Validation Error", "Name is required.");
            return false;
        }
        if (emailField.getText().isBlank()) {
            showAlert("Validation Error", "Email is required.");
            return false;
        }
        if (passwordField.getText().isBlank()) {
            showAlert("Validation Error", "Password is required.");
            return false;
        }
        return true;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
