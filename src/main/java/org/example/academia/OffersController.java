package org.example.labsheet4;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class OffersController {

    @FXML private TextField cidField;
    @FXML private TextField mcodeField;
    @FXML private TextField academicYearField;
    @FXML private TextField semesterField;
    @FXML private Label statusLabel;

    @FXML
    private void handleInsert() throws SQLException {
        String sql = "INSERT INTO Offers (CID, MCode, Academic_year, Semester) VALUES (?, ?, ?, ?)";
        executeOffersSQL(sql, false, false);
        statusLabel.setText("Offer inserted successfully!");
    }

    @FXML
    private void handleUpdate() throws SQLException {
        String sql = "UPDATE Offers SET MCode=?, Academic_year=?, Semester=? WHERE CID=?";
        executeOffersSQL(sql, true, false);
        statusLabel.setText("Offer updated successfully!");
    }

    @FXML
    private void handleDelete() throws SQLException {
        String sql = "DELETE FROM Offers WHERE CID=?";
        executeOffersSQL(sql, false, true);
        statusLabel.setText("Offer deleted successfully!");
    }

    private void executeOffersSQL(String sql, boolean isUpdate, boolean isDelete) throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Labsheet 4;encrypt=false;";
        String user = "sa";
        String password = "uthu";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (isDelete) {
                stmt.setString(1, cidField.getText());
            } else if (isUpdate) {
                stmt.setString(1, mcodeField.getText());
                stmt.setInt(2, Integer.parseInt(academicYearField.getText()));
                stmt.setInt(3, Integer.parseInt(semesterField.getText()));
                stmt.setString(4, cidField.getText());
            } else {
                stmt.setString(1, cidField.getText());
                stmt.setString(2, mcodeField.getText());
                stmt.setInt(3, Integer.parseInt(academicYearField.getText()));
                stmt.setInt(4, Integer.parseInt(semesterField.getText()));
            }

            stmt.executeUpdate();
            clearForm();
        } catch (NumberFormatException e) {
            statusLabel.setText("Academic Year and Semester must be numbers!");
        }
    }

    private void clearForm() {
        cidField.clear();
        mcodeField.clear();
        academicYearField.clear();
        semesterField.clear();
    }
}
