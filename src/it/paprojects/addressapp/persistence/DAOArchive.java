package it.paprojects.addressapp.persistence;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import it.paprojects.addressapp.App;
import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.BeansEnum;
import it.paprojects.addressapp.model.Model;
import it.paprojects.addressapp.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.prefs.Preferences;

public class DAOArchive implements IDAOArchive {
    private static final Logger logger = LoggerFactory.getLogger(DAOArchive.class);
    private final GsonBuilder gsonBuilder;

    public DAOArchive() {
        this.gsonBuilder = new GsonBuilder();
    }

    @Override
    public List<Person> load(String filePath) throws DAOException {
        Gson gson = gsonBuilder
                .registerTypeAdapter(Person.class, new PersonAdapter())
                .create();

        List<Person> personList = null;
        try (Reader reader = new FileReader(filePath)) {
            personList = gson.fromJson(reader, new TypeToken<List<Person>>() {}.getType());
            logger.debug("List loading...: " + personList.size() + " people");
            setPersonFilePath(filePath);
            return personList;
        } catch (Exception ex) {
            throw new DAOException("Cannot load json file: " + filePath + "\n" + ex.getMessage());
        }
    }

    @Override
    public void save(String filePath) throws DAOException {
        Gson gson = gsonBuilder
                .registerTypeAdapter(Person.class, new PersonAdapter())
                .setPrettyPrinting()
                .create();
        Writer writer = null;
        Archive archive = (Archive) Model.getBean(BeansEnum.ARCHIVE);
        List<Person> personList = archive.getPersonData();

        try {
            writer = new FileWriter(filePath);
            gson.toJson(personList, writer);
            writer.flush();
            writer.close();
            setPersonFilePath(filePath);
        } catch (Exception ex) {
            throw new DAOException("Cannot save archive to json file!" + "\n" + ex.getMessage());
        }
    }

    @Override
    public File getPersonFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    private void setPersonFilePath(String filePath) {
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        prefs.put("filePath", filePath);
    }

    @Override
    public void setPersonFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(App.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            App.getPrimaryStage().setTitle("AddressApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            App.getPrimaryStage().setTitle("AddressApp");
        }
    }
}
