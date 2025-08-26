package org.example.labsheet4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class HelloController {

    @FXML private TextField cidField;
    @FXML private TextField cnameField;
    @FXML private TextField cDescriptionField;
    @FXML private TextField cFeeField;
    @FXML private Label statusLabel;

    @FXML
    private void handleInsert() throws SQLException {
        String sql = "INSERT INTO Course (CID, CName, C_Description, C_Fee) VALUES (?, ?, ?, ?)";
        executeCourseSQL(sql, false, false);
        statusLabel.setText("Course inserted successfully!");
    }

    @FXML
    private void handleUpdate() throws SQLException {
        String sql = "UPDATE Course SET CName=?, C_Description=?, C_Fee=? WHERE CID=?";
        executeCourseSQL(sql, true, false);
        statusLabel.setText("Course updated successfully!");
    }

    @FXML
    private void handleDelete() throws SQLException {
        String sql = "DELETE FROM Course WHERE CID=?";
        executeCourseSQL(sql, false, true);
        statusLabel.setText("Course deleted successfully!");
    }

    private void executeCourseSQL(String sql, boolean isUpdate, boolean isDelete) throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Labsheet 4;encrypt=false;";
        String user = "sa";
        String password = "uthu";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (isDelete) {
                stmt.setString(1, cidField.getText());
            } else if (isUpdate) {
                stmt.setString(1, cnameField.getText());
                stmt.setString(2, cDescriptionField.getText());
                stmt.setDouble(3, Double.parseDouble(cFeeField.getText()));
                stmt.setString(4, cidField.getText());
            } else { // Insert
                stmt.setString(1, cidField.getText());
                stmt.setString(2, cnameField.getText());
                stmt.setString(3, cDescriptionField.getText());
                stmt.setDouble(4, Double.parseDouble(cFeeField.getText()));
            }

            stmt.executeUpdate();
            clearForm();
        } catch (NumberFormatException e) {
            statusLabel.setText("Fee must be a number!");
        }
    }

    private void clearForm() {
        cidField.clear();
        cnameField.clear();
        cDescriptionField.clear();
        cFeeField.clear();
    }
}
