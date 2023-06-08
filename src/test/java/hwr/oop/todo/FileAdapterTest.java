package hwr.oop.todo;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.persistence.FileAdapter;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.UUID;

class FileAdapterTest {

    @Test
    void canSaveToFile() {
        FileAdapter fileAdapter = new FileAdapter();
        File file = new File("save.csv");
        fileAdapter.save(new Project(UUID.randomUUID(), "Test").toCSV(), file);
    }
}
