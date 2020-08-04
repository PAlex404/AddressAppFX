package it.paprojects.addressapp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RootLayout {
    private BorderPane pane;

    public BorderPane getPane() {
        return this.pane;
    }

    public void buildAndShowGUI(Stage primaryStage) {
        try {
            pane = FXMLLoader.load(getClass().getResource("RootLayout.fxml"));
            primaryStage.setTitle("Address App");
            primaryStage.setScene(new Scene(pane));
            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();        }
    }
}
