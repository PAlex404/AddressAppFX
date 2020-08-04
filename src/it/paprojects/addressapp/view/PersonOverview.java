package it.paprojects.addressapp.view;

import it.paprojects.addressapp.control.PersonOverviewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PersonOverview {
    private final FXMLLoader loader;

    public PersonOverview() {
        this.loader = new FXMLLoader();
        this.loader.setLocation(getClass().getResource("PersonOverview.fxml"));
    }

    public void buildAndCenterPanel(BorderPane borderPane) {
        try {
            AnchorPane anchorPane = loader.load();

            initController();

            borderPane.setCenter(anchorPane);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initController() {
        PersonOverviewController controller = loader.getController();
        controller.setData();
    }
}
