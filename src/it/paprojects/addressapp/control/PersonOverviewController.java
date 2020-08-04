package it.paprojects.addressapp.control;

import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.model.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.time.format.DateTimeFormatter;

public class PersonOverviewController {

    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> firstNameColumn;

    @FXML
    private Label lastNameLabel;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label birthdayLabel;

    @FXML
    public void initialize() {
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setData() {
        Archive archive = (Archive) Model.getBean(BeansEnum.ARCHIVE);
        personTable.setItems(archive.getPersonData());
    }

    private void showPersonDetails(Person person) {
        lastNameLabel.setText(person.getLastName());
        firstNameLabel.setText(person.getFirstName());
        streetLabel.setText(person.getStreet());
        cityLabel.setText(person.getCity());
        postalCodeLabel.setText(String.valueOf(person.getPostalCode()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        birthdayLabel.setText(person.getBirthday().format(formatter));
    }
}
