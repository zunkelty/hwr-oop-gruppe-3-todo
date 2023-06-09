package hwr.oop.todo.persistence;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseAdapterTest {
    private static Connection connection;
    @BeforeAll
    public static void setupBeforeAll() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            connection = DriverManager.getConnection("jdbc:sqlite::memory:");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getSchemaSetupScripts() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/scripts/schema.sql"))).split(";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] getSeedScripts() {
        try {
            return new String(Files.readAllBytes(Paths.get("src/scripts/seed.sql"))).split(";");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setup() {
        String[] schemaSetupScripts = getSchemaSetupScripts();
        executeAllScripts(schemaSetupScripts);

        String[] seedScripts = getSeedScripts();
        executeAllScripts(seedScripts);
    }

    public void executeAllScripts(String[] scripts){
        try {
            Statement statement = connection.createStatement();

            for (String seedScript : scripts) {
                statement.addBatch(seedScript);
            }

            statement.executeBatch();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void canGetTaskById() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        UUID taskId = UUID.fromString("00000000-0000-0000-0000-000000000001");

        Task task = databaseAdapter.getTask(taskId);

        assertEquals("Water plants", task.getTitle());
    }

    @Test
    void canInsertTask(){
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Task writtenTask = TaskFactory.createTask("Test Task", "Test Description");

        databaseAdapter.insertTask(writtenTask);

        Task readTask = databaseAdapter.getTask(writtenTask.getId());

        assertEquals(writtenTask.getId(), readTask.getId());
    }

    @Test
    void canHandleFailedDatabaseStatements() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection){
            @Override
            public void insertTask(Task task) throws FailedDatabaseStatementException {
                throw new FailedDatabaseStatementException(new Exception("Test"));
            }
        };

        Task task = TaskFactory.createTask("Test Task", "Test Description");
        assertThrows(FailedDatabaseStatementException.class, () -> databaseAdapter.insertTask(task));
        databaseAdapter.deleteTask(task);
    }

    @Test
    void canDeleteTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        Task writtenTask = databaseAdapter.getTask(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        databaseAdapter.deleteTask(writtenTask);

        Task readTask = databaseAdapter.getTask(writtenTask.getId());

        assertNull(readTask);
    }

    @Test
    void canInsertProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Project project = ProjectFactory.createProject("Test Project");

        databaseAdapter.insertProject(project);

        Project p = databaseAdapter.getProject(project.getId());

        assertEquals(project.getId(), p.getId());
    }

    @Test
    void canDeleteProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        Project project = databaseAdapter.getProject(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        databaseAdapter.deleteProject(project);

        Project p = databaseAdapter.getProject(project.getId());

        assertNull(p);
    }

    @Test
    void canUpdateProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Project project = ProjectFactory.createProject("Test Project");

        databaseAdapter.insertProject(project);

        project.setName("Updated Test Project");
        databaseAdapter.updateProject(project);

        Project p = databaseAdapter.getProject(project.getId());

        assertEquals("Updated Test Project", p.getName());
    }

    @Test
    void canInsertTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Tag tag = TagFactory.createTag("Test Tag");

        databaseAdapter.insertTag(tag);
        Tag t = databaseAdapter.getTag(tag.getId());

        assertEquals(tag.getId(), t.getId());
    }

    @Test
    void canDeleteTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        Tag tag = databaseAdapter.getTag(UUID.fromString("00000000-0000-0000-0000-000000000001"));
        databaseAdapter.deleteTag(tag);

        Tag t = databaseAdapter.getTag(tag.getId());

        assertNull(t);
    }

    @Test
    void canUpdateTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Tag tag = TagFactory.createTag("Test Tag");

        databaseAdapter.insertTag(tag);

        tag.setName("Updated Test Tag");
        databaseAdapter.updateTag(tag);

        Tag t = databaseAdapter.getTag(tag.getId());

        assertEquals("Updated Test Tag", t.getName());
    }

    @Test
    void canUpdateTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Task task = databaseAdapter.getTask(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        task.setTitle("Updated Test Task");
        databaseAdapter.updateTask(task);

        Task t = databaseAdapter.getTask(task.getId());

        assertEquals("Updated Test Task", t.getTitle());
    }
}
