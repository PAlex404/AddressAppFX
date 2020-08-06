package it.paprojects.addressapp.view;

import it.paprojects.addressapp.control.BirthdayStatisticsController;
import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class BirthdayStatistics {
    private final FXMLLoader loader;

    public BirthdayStatistics() {
        this.loader = new FXMLLoader();
        this.loader.setLocation(getClass().getResource("BirthdayStatistics.fxml"));
    }

    public void buildAndShow(Stage primaryStage) {
        try {
            AnchorPane pane = loader.load();
            Stage statsStage = new Stage();
            statsStage.setTitle("Birthday Statistics");
            statsStage.initModality(Modality.WINDOW_MODAL);
            statsStage.initOwner(primaryStage);
            statsStage.setScene(new Scene(pane));

            initController();

            statsStage.show();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initController() {
        BirthdayStatisticsController controller = loader.getController();
        Archive archive = (Archive) Model.getBean(BeansEnum.ARCHIVE);
        controller.setData(archive.getPersonData());
    }
}
