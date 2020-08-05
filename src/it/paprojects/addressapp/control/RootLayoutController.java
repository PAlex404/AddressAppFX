package it.paprojects.addressapp.control;

import it.paprojects.addressapp.App;
import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.persistence.DAOArchive;
import it.paprojects.addressapp.persistence.DAOException;
import it.paprojects.addressapp.persistence.IDAOArchive;
import it.paprojects.addressapp.view.AlertDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import java.io.File;

public class RootLayoutController {
    // TODO Implement tools menu buttons without code repetition
    private IDAOArchive dao;

    @FXML
    private void initialize() {
        this.dao = new DAOArchive();
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = buildDefaultFileChooser();
        File file = fileChooser.showOpenDialog(App.getPrimaryStage());

        if (file != null) {
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            try {
                Archive archive = (Archive) Model.getBean(BeansEnum.ARCHIVE);
                archive.setPersonData(dao.load(file.getAbsolutePath()));
            } catch (DAOException daoe) {
                AlertDialog alert = new AlertDialog(Alert.AlertType.ERROR, App.getPrimaryStage(), daoe.getMessage(), "Address App - Loading file...");
                alert.show();
            }
        }
    }

    @FXML
    private void handleSave() {
        File personFile = dao.getPersonFilePath();
        if (personFile != null) {
            try {
                dao.save(personFile.getAbsolutePath());
            } catch (DAOException daoe) {
                AlertDialog alert = new AlertDialog(Alert.AlertType.ERROR, App.getPrimaryStage(), daoe.getMessage(), "Address App - Saving file...");
                alert.show();
            }
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = buildDefaultFileChooser();
        File file = fileChooser.showSaveDialog(App.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".json")) {
                file = new File(file.getPath() + ".json");
            }
            try {
                dao.save(file.getAbsolutePath());
            } catch (DAOException daoe) {
                AlertDialog alert = new AlertDialog(Alert.AlertType.ERROR, App.getPrimaryStage(), daoe.getMessage(), "Address App - Saving file...");
                alert.show();
            }
        }
    }

    @FXML
    private void handleAbout() {
        AlertDialog dialog = new AlertDialog(Alert.AlertType.INFORMATION, "Address App", "Author: Alessandro Parisi\nTutorial by code.makery: https://code.makery.ch/it/library/javafx-tutorial/", "About");
        dialog.show();
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    private FileChooser buildDefaultFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JSON files (*.json)", "*.json");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.dir") + "/data"));
        return fileChooser;
    }
}
