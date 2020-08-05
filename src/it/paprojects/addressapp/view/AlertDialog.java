package it.paprojects.addressapp.view;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class AlertDialog {
    private Alert alert;

    public AlertDialog(Alert.AlertType type, Stage stage, String context) {
        this.alert = new Alert(type);
        this.alert.initOwner(stage);
        this.alert.setContentText(context);
    }

    public AlertDialog(Alert.AlertType type, String title, String context, String header) {
        this.alert = new Alert(type);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.setContentText(context);
    }

    public AlertDialog(Alert.AlertType type, Stage stage, String context, String title) {
        this.alert = new Alert(type);
        this.alert.initOwner(stage);
        this.alert.setTitle(title);
        this.alert.setContentText(context);

    }

    public AlertDialog(Alert.AlertType type, Stage stage, String context, String title, String header) {
        this.alert = new Alert(type);
        this.alert.initOwner(stage);
        this.alert.setTitle(title);
        this.alert.setHeaderText(header);
        this.alert.setContentText(context);

    }

    public void show() {
        this.alert.showAndWait();
    }
}
