package it.paprojects.addressapp.view;

import it.paprojects.addressapp.control.PersonAddEditController;
import it.paprojects.addressapp.model.Person;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class AddEditDialog {
    private final FXMLLoader loader;

    public AddEditDialog() {
        this.loader = new FXMLLoader();
        this.loader.setLocation(getClass().getResource("PersonAddEditDialog.fxml"));
    }

    public void buildAndShowDialog(Stage primaryStage, Person selectedPerson) {
        try {
            AnchorPane anchorPane = loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add/Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            dialogStage.setScene(new Scene(anchorPane));

            initController(dialogStage, selectedPerson);

            dialogStage.showAndWait();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void initController(Stage dialogStage, Person selectedPerson) {
        PersonAddEditController controller = loader.getController();
        if (selectedPerson != null) {
            controller.initLabels(selectedPerson);
        }
    }
}
