package org.example.labsheet4;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.*;

public class ModuleController {

    @FXML private TextField mcodeField;
    @FXML private TextField mnameField;
    @FXML private TextField mDescriptionField;
    @FXML private TextField noOfCreditsField;
    @FXML private Label statusLabel;

    @FXML
    private void handleInsert() throws SQLException {
        String sql = "INSERT INTO Module (MCode, MName, M_Description, NoOfCredits) VALUES (?, ?, ?, ?)";
        executeModuleSQL(sql, false, false);
        statusLabel.setText("Module inserted successfully!");
    }

    @FXML
    private void handleUpdate() throws SQLException {
        String sql = "UPDATE Module SET MName=?, M_Description=?, NoOfCredits=? WHERE MCode=?";
        executeModuleSQL(sql, true, false);
        statusLabel.setText("Module updated successfully!");
    }

    @FXML
    private void handleDelete() throws SQLException {
        String sql = "DELETE FROM Module WHERE MCode=?";
        executeModuleSQL(sql, false, true);
        statusLabel.setText("Module deleted successfully!");
    }

    private void executeModuleSQL(String sql, boolean isUpdate, boolean isDelete) throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=Labsheet 4;encrypt=false;";
        String user = "sa";
        String password = "uthu";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (isDelete) {
                stmt.setString(1, mcodeField.getText());
            } else if (isUpdate) {
                stmt.setString(1, mnameField.getText());
                stmt.setString(2, mDescriptionField.getText());
                stmt.setInt(3, Integer.parseInt(noOfCreditsField.getText()));
                stmt.setString(4, mcodeField.getText());
            } else {
                stmt.setString(1, mcodeField.getText());
                stmt.setString(2, mnameField.getText());
                stmt.setString(3, mDescriptionField.getText());
                stmt.setInt(4, Integer.parseInt(noOfCreditsField.getText()));
            }

            stmt.executeUpdate();
            clearForm();
        } catch (NumberFormatException e) {
            statusLabel.setText("Credits must be a number!");
        }
    }

    private void clearForm() {
        mcodeField.clear();
        mnameField.clear();
        mDescriptionField.clear();
        noOfCreditsField.clear();
    }
}
