package org.example.labsheet4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class OffersApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        // Load the Offers form FXML
        FXMLLoader fxmlLoader = new FXMLLoader(OffersApplication.class.getResource("offers.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300); // Adjust size as needed
        stage.setTitle("Offers Form"); // Set title for Offers form
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
