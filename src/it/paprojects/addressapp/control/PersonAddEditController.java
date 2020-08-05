package it.paprojects.addressapp.control;

import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.model.Person;
import it.paprojects.addressapp.view.AlertDialog;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
    private DatePicker datePicker;

    private Person selectedPerson;

    @FXML
    private void initialize() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        datePicker.setConverter(new StringConverter<>() {
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
    }

    public void init(Person selectedPerson) {
        this.selectedPerson = selectedPerson;

        if (selectedPerson != null) {
            lastNameField.setText(selectedPerson.getLastName());
            firstNameField.setText(selectedPerson.getFirstName());
            streetField.setText(selectedPerson.getStreet());
            cityField.setText(selectedPerson.getCity());
            postalCodeField.setText(String.valueOf(selectedPerson.getPostalCode()));
            datePicker.setValue(selectedPerson.getBirthday());
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
                datePicker.getValue()
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
        if (datePicker.getValue() == null) {
            errors.append("Birthday date not valid!\n");
        }
        return errors.toString();
    }

    @FXML
    private void handleCancel() {
        Stage dialogStage = (Stage) dialogPane.getScene().getWindow();
        dialogStage.close();
    }
}
