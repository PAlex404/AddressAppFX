package it.paprojects.addressapp.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Archive {
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public ObservableList<Person> getPersonData() {
        return this.personData;
    }

    public void addPerson(Person person) {
        this.personData.add(person);
    }

    public void setPersonData(ObservableList<Person> personData) {
        this.personData = personData;
    }
}
