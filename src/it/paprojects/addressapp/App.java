package it.paprojects.addressapp;

import it.paprojects.addressapp.view.PersonOverview;
import it.paprojects.addressapp.view.RootLayout;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        RootLayout rootLayout = new RootLayout();
        rootLayout.buildAndShowGUI(primaryStage);

        PersonOverview personOverview = new PersonOverview();
        personOverview.buildAndCenterPanel(rootLayout.getPane());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
