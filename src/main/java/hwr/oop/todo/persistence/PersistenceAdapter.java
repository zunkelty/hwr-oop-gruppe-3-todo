package hwr.oop.todo.persistence;

import java.io.File;
import java.util.List;

public class PersistenceAdapter {

    FileAdapter fileAdapter = new FileAdapter();

    public void save(Persistable persistable, File file) {
        fileAdapter.save(persistable.toCSV(), file);
    }

    public Persistable load() {
        List<String> fileContents = fileAdapter.load("save.csv");
        return null;
    }
}
