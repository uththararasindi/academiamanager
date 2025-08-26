package org.example.labsheet4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ModuleApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Load the Module form FXML
        FXMLLoader fxmlLoader = new FXMLLoader(ModuleApplication.class.getResource("module.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300); // Adjusted size for module form
        stage.setTitle("Module Form"); // Updated title
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
