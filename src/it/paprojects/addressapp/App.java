package it.paprojects.addressapp;

import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.model.Person;
import it.paprojects.addressapp.view.PersonOverview;
import it.paprojects.addressapp.view.RootLayout;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.time.LocalDate;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        initMockModel();

        RootLayout rootLayout = new RootLayout();
        rootLayout.buildAndShowGUI(primaryStage);

        PersonOverview personOverview = new PersonOverview();
        personOverview.buildAndCenterPanel(rootLayout.getPane());
    }

    private void initMockModel() {
        Archive archive = new Archive();

        ObservableList<Person> personData = archive.getPersonData();

        Person p1 = new Person("Rossi", "Mario", "Via Ciccotti", "Roma", 120, LocalDate.of(1970, 1, 28));
        Person p2 = new Person("Stroll", "Rocco", "Via Nuova", "Parma", 87, LocalDate.of(1995, 5, 24));
        Person p3 = new Person("Richot", "Armand", "Viale Orin", "Reinia", 77, LocalDate.of(1985, 3, 14));
        Person p4 = new Person("Fico", "Franco", "Via Vecchia", "Pertugia", 111, LocalDate.of(1960, 9, 3));
        Person p5 = new Person("Cattivaparte", "Napolione", "Via Francia", "Gheiland", 254, LocalDate.of(1977, 12, 26));

        personData.addAll(p1, p2, p3, p4, p5);
        archive.setPersonData(personData);
        Model.putBean(BeansEnum.ARCHIVE, archive);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
