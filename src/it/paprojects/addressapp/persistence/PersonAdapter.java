package it.paprojects.addressapp.persistence;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import it.paprojects.addressapp.model.Person;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PersonAdapter extends TypeAdapter<Person> {

    @Override
    public void write(JsonWriter writer, Person person) throws IOException {
        writer.beginObject();
        writer.name("lastName");
        writer.value(person.getLastName());
        writer.name("firstName");
        writer.value(person.getFirstName());
        writer.name("street");
        writer.value(person.getStreet());
        writer.name("city");
        writer.value(person.getCity());
        writer.name("postalCode");
        writer.value(person.getPostalCode());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
        writer.name("birthday");
        writer.value(person.getBirthday().format(formatter));
        writer.endObject();
    }

    @Override
    public Person read(JsonReader reader) throws IOException {
        reader.beginObject();
        String fieldName = null;

        String lastName = "";
        String firstName = "";
        String street = "";
        String city = "";
        int postalCode = -1;
        LocalDate localDate = LocalDate.EPOCH;

        while (reader.hasNext()) {
            JsonToken token = reader.peek();

            if (token.equals(JsonToken.NAME)) {
                fieldName = reader.nextName();
            }

            switch (fieldName) {
                case "lastName" -> {
                    token = reader.peek();
                    lastName = reader.nextString();
                }
                case "firstName" -> {
                    token = reader.peek();
                    firstName = reader.nextString();
                }
                case "street" -> {
                    token = reader.peek();
                    street = reader.nextString();
                }
                case "city" -> {
                    token = reader.peek();
                    city = reader.nextString();
                }
                case "postalCode" -> {
                    token = reader.peek();
                    postalCode = reader.nextInt();
                }
                case "birthday" -> {
                    token = reader.peek();
                    String birthday = reader.nextString();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy");
                    localDate = LocalDate.parse(birthday, formatter);
                }
            }
        }
        reader.endObject();
        return new Person(lastName, firstName, street, city, postalCode, localDate);
    }
}
