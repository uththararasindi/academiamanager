package org.example.labsheet4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StudentApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the Student form FXML
        FXMLLoader fxmlLoader = new FXMLLoader(StudentApplication.class.getResource("student.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400); // Adjusted size for student form
        stage.setTitle("Student Form");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
