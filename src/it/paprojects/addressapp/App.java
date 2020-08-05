package it.paprojects.addressapp;

import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.persistence.DAOArchive;
import it.paprojects.addressapp.persistence.DAOException;
import it.paprojects.addressapp.persistence.IDAOArchive;
import it.paprojects.addressapp.view.AlertDialog;
import it.paprojects.addressapp.view.PersonOverview;
import it.paprojects.addressapp.view.RootLayout;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {
    private IDAOArchive dao;
    // TODO Maybe there's a better solution but I don't know how
    private static Stage pStage;

    public static Stage getPrimaryStage() {
        return pStage;
    }

    @Override
    public void start(Stage primaryStage) {
        pStage = primaryStage;

        initModel();

        RootLayout rootLayout = new RootLayout();
        rootLayout.buildAndShowGUI(primaryStage);

        PersonOverview personOverview = new PersonOverview();
        personOverview.buildAndCenterPanel(rootLayout.getPane());
    }

    private void initModel() {
        this.dao = new DAOArchive();
        File file = dao.getPersonFilePath();
        Archive archive = new Archive();
        if (file.exists()) {
            try {
                archive.addPersonData(dao.load(file.getAbsolutePath()));
            } catch (DAOException daoe) {
                AlertDialog alert = new AlertDialog(Alert.AlertType.ERROR, App.getPrimaryStage(), daoe.getMessage(), "Address App - Model initialization...");
                alert.show();
            }
        }
        Model.putBean(BeansEnum.ARCHIVE, archive);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
