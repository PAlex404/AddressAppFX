package it.paprojects.addressapp.view;

import it.paprojects.addressapp.control.RootLayoutController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RootLayout {
    private final FXMLLoader loader;

    private BorderPane pane;

    public BorderPane getPane() {
        return this.pane;
    }

    public RootLayout() {
        this.loader = new FXMLLoader();
        this.loader.setLocation(getClass().getResource("RootLayout.fxml"));
    }

    public void buildAndShowGUI(Stage primaryStage) {
        try {
            pane = loader.load();
            primaryStage.setTitle("Address App");
            primaryStage.getIcons().add(new Image("file:resources/appicon16.png"));
            primaryStage.getIcons().add(new Image("file:resources/appicon32.png"));
            primaryStage.getIcons().add(new Image("file:resources/appicon64.png"));
            primaryStage.setScene(new Scene(pane));

            initController();

            primaryStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initController() {
        RootLayoutController controller = loader.getController();
    }
}
