package it.paprojects.addressapp.persistence;

import it.paprojects.addressapp.model.Archive;
import it.paprojects.addressapp.model.Person;

import java.io.File;
import java.util.List;

public interface IDAOArchive {
    List<Person> load(String filePath) throws DAOException;
    void save(String filePath) throws DAOException;
    File getPersonFilePath();
    void setPersonFilePath(File file);
}
