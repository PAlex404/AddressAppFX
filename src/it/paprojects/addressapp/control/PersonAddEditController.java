package it.paprojects.addressapp.control;

import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.model.Person;
import it.paprojects.addressapp.view.AlertDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PersonAddEditController {

    @FXML
    private AnchorPane dialogPane;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField birthdayField;

    private Person selectedPerson;

    public void init(Person selectedPerson) {
        this.selectedPerson = selectedPerson;

        if (selectedPerson != null) {
            lastNameField.setText(selectedPerson.getLastName());
            firstNameField.setText(selectedPerson.getFirstName());
            streetField.setText(selectedPerson.getStreet());
            cityField.setText(selectedPerson.getCity());
            postalCodeField.setText(String.valueOf(selectedPerson.getPostalCode()));
            birthdayField.setText(selectedPerson.getBirthday().format(DateTimeFormatter.ofPattern("dd/MMMM/yyyy")));
        }
    }

    @FXML
    private void handleOk() {
        Stage dialogStage = (Stage) dialogPane.getScene().getWindow();
        String errors = checkInput();
        if (!errors.isEmpty()) {
            AlertDialog alert = new AlertDialog(Alert.AlertType.ERROR, dialogStage, errors, "Address App - Input check...", "Please correct invalid fields!");
            alert.show();
            return;
        }

        Person tmpPerson = new Person(
                lastNameField.getText(),
                firstNameField.getText(),
                streetField.getText(),
                cityField.getText(),
                Integer.parseInt(postalCodeField.getText()),
                LocalDate.parse(birthdayField.getText(), DateTimeFormatter.ofPattern("dd/MMMM/yyyy"))
        );

        if (this.selectedPerson == null) {
            Archive archive = (Archive) Model.getBean(BeansEnum.ARCHIVE);
            archive.addPerson(tmpPerson);
        } else {
            this.selectedPerson.set(tmpPerson);
        }
        dialogStage.close();
    }

    private String checkInput() {
        StringBuilder errors = new StringBuilder();

        if (lastNameField.getText() == null || lastNameField.getText().trim().isEmpty()) {
            errors.append("Last name not valid!\n");
        }
        if (firstNameField.getText() == null || firstNameField.getText().trim().isEmpty()) {
            errors.append("First name not valid!\n");
        }
        if (streetField.getText() == null || streetField.getText().trim().isEmpty()) {
            errors.append("Street not valid!\n");
        }
        if (cityField.getText() == null || cityField.getText().trim().isEmpty()) {
            errors.append("City not valid!\n");
        }
        if (postalCodeField.getText() == null || postalCodeField.getText().trim().isEmpty()) {
            errors.append("Postal code not valid!\n");
        } else {
            try {
                Integer.parseInt(postalCodeField.getText());
            } catch (NumberFormatException nfe) {
                errors.append("Postal code not valid, must be an integer!\n");
            }
        }
        if (birthdayField.getText() == null || birthdayField.getText().trim().isEmpty()) {
            errors.append("Birthday date not valid!\n");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
                LocalDate.parse(birthdayField.getText(), formatter);
            } catch (DateTimeParseException dtpe) {
                errors.append("Birthday date is not valid. Use the format dd/MMMM/yyyy !\n");
            }
        }
        return errors.toString();
    }

    @FXML
    private void handleCancel() {
        Stage dialogStage = (Stage) dialogPane.getScene().getWindow();
        dialogStage.close();
    }
}
