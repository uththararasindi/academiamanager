package org.example.labsheet4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentController {

    @FXML private TextField sidField;
    @FXML private TextField snameField;
    @FXML private TextField addressField;
    @FXML private DatePicker dobPicker;
    @FXML private TextField nicField;
    @FXML private TextField cidField;
    @FXML private Label statusLabel;

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=Labsheet 4;encrypt=false;";
    private final String user = "sa";
    private final String password = "uthu";

    @FXML
    private void handleInsert() {
        String sql = "INSERT INTO Students (SID, SName, Address, DOB, NIC, CID) VALUES (?, ?, ?, ?, ?, ?)";
        executeStudentSQL(sql, false, false);
    }

    @FXML
    private void handleUpdate() {
        String sql = "UPDATE Students SET SName=?, Address=?, DOB=?, NIC=?, CID=? WHERE SID=?";
        executeStudentSQL(sql, true, false);
    }

    @FXML
    private void handleDelete() {
        String sql = "DELETE FROM Students WHERE SID=?";
        executeStudentSQL(sql, false, true);
    }

    private void executeStudentSQL(String sql, boolean isUpdate, boolean isDelete) {
        String sid = sidField.getText();
        if (sid.isEmpty()) {
            statusLabel.setText("SID cannot be empty!");
            return;
        }

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (isDelete) {
                stmt.setString(1, sid);
            } else if (isUpdate) {
                if (dobPicker.getValue() == null) {
                    statusLabel.setText("Please select a Date of Birth!");
                    return;
                }
                stmt.setString(1, snameField.getText());
                stmt.setString(2, addressField.getText());
                stmt.setDate(3, Date.valueOf(dobPicker.getValue()));
                stmt.setString(4, nicField.getText());
                stmt.setString(5, cidField.getText());
                stmt.setString(6, sid);
            } else { // Insert
                if (dobPicker.getValue() == null) {
                    statusLabel.setText("Please select a Date of Birth!");
                    return;
                }
                stmt.setString(1, sid);
                stmt.setString(2, snameField.getText());
                stmt.setString(3, addressField.getText());
                stmt.setDate(4, Date.valueOf(dobPicker.getValue()));
                stmt.setString(5, nicField.getText());
                stmt.setString(6, cidField.getText());
            }

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                statusLabel.setText(isDelete ? "Student deleted successfully!" :
                        isUpdate ? "Student updated successfully!" : "Student inserted successfully!");
                showAlert(AlertType.INFORMATION, statusLabel.getText());
                clearForm();
            }

        } catch (SQLException e) {
            statusLabel.setText("SQL Error: " + e.getMessage());
            showAlert(AlertType.ERROR, e.getMessage());
        }
    }

    private void clearForm() {
        sidField.clear();
        snameField.clear();
        addressField.clear();
        dobPicker.setValue(null);
        nicField.clear();
        cidField.clear();
    }

    private void showAlert(AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(type == AlertType.INFORMATION ? "Success" : "Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
