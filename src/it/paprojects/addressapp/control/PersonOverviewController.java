package it.paprojects.addressapp.control;

import it.paprojects.addressapp.App;
import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.model.Person;
import it.paprojects.addressapp.view.AddEditDialog;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private Button editButton;
    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
        editButton.disableProperty().bind(Bindings.isEmpty(personTable.getSelectionModel().getSelectedItems()));
        deleteButton.disableProperty().bind(Bindings.isEmpty(personTable.getSelectionModel().getSelectedItems()));
    }

    public void setData() {
        Archive archive = (Archive) Model.getBean(BeansEnum.ARCHIVE);
        personTable.setItems(archive.getPersonData());
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            lastNameLabel.setText(person.getLastName());
            firstNameLabel.setText(person.getFirstName());
            streetLabel.setText(person.getStreet());
            cityLabel.setText(person.getCity());
            postalCodeLabel.setText(String.valueOf(person.getPostalCode()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
            birthdayLabel.setText(person.getBirthday().format(formatter));
        } else {
            lastNameLabel.setText("");
            firstNameLabel.setText("");
            streetLabel.setText("");
            cityLabel.setText("");
            postalCodeLabel.setText("");
            birthdayLabel.setText("");
        }
    }

    @FXML
    private void handleAdd() {
        AddEditDialog dialog = new AddEditDialog();
        dialog.buildAndShowDialog(App.getPrimaryStage(), null);

        // Force update but it should be automatic
        update();
    }

    @FXML
    private void handleEdit() {
        AddEditDialog dialog = new AddEditDialog();
        Person selectedPerson = personTable.getSelectionModel().getSelectedItem();
        Model.putBean(BeansEnum.PERSON_EDIT_DIALOG, selectedPerson);
        dialog.buildAndShowDialog(App.getPrimaryStage(), selectedPerson);

        // Force update but it should be automatic
        update();
    }

    @FXML
    private void handleDelete() {
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        personTable.getItems().remove(selectedIndex);
    }

    private void update() {
        personTable.refresh();
        showPersonDetails(personTable.getSelectionModel().getSelectedItem());
    }
}
