package hwr.oop.todo.persistence;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;
import hwr.oop.todo.library.todolist.NotFoundException;
import hwr.oop.todo.library.todolist.ToDoList;
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

    private Task getTaskById(UUID id) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("SELECT * FROM Tasks WHERE id = '%s'", id);
            ResultSet resultSet = statement.executeQuery(sql);

            if(!resultSet.next()){
                throw new NotFoundException("Task");
            }

            String title = resultSet.getString("title");
            String description = resultSet.getString("description");
            TaskState state = TaskState.valueOf(resultSet.getString("state"));
            Task task = new Task(id, title, description, state);

            resultSet.close();
            statement.close();

            return task;
        } catch (SQLException e) {
            throw new FailedDatabaseStatementException(e);
        }
    }

    @Test
    void canGetTaskById() {
        UUID taskId = UUID.fromString("00000000-0000-0000-0000-000000000001");

        Task task = getTaskById(taskId);

        assertEquals("Water plants", task.getTitle());
    }

    @Test
    void canInsertTask(){
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Task writtenTask = TaskFactory.createTask("Test Task", "Test Description");

        databaseAdapter.createTask(writtenTask);

        Task readTask = getTaskById(writtenTask.getId());

        assertEquals(writtenTask.getId(), readTask.getId());
    }

    @Test
    void canHandleFailedDatabaseStatements() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection){
            @Override
            public void createTask(Task task) throws FailedDatabaseStatementException {
                throw new FailedDatabaseStatementException(new Exception("Test"));
            }
        };

        Task task = TaskFactory.createTask("Test Task", "Test Description");
        assertThrows(FailedDatabaseStatementException.class, () -> databaseAdapter.createTask(task));
        databaseAdapter.deleteTask(task);
    }

    @Test
    void canDeleteTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        Task writtenTask = getTaskById(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        databaseAdapter.deleteTask(writtenTask);

        UUID taskId = writtenTask.getId();
        assertThrows(NotFoundException.class, () -> getTaskById(taskId));
    }

    @Test
    void canInsertProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Project project = ProjectFactory.createProject("Test Project");

        databaseAdapter.createProject(project);

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

        databaseAdapter.createProject(project);

        project.setName("Updated Test Project");
        databaseAdapter.updateProject(project);

        Project p = databaseAdapter.getProject(project.getId());

        assertEquals("Updated Test Project", p.getName());
    }

    @Test
    void canInsertTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Tag tag = TagFactory.createTag("Test Tag");

        databaseAdapter.createTag(tag);
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

        databaseAdapter.createTag(tag);

        tag.setName("Updated Test Tag");
        databaseAdapter.updateTag(tag);

        Tag t = databaseAdapter.getTag(tag.getId());

        assertEquals("Updated Test Tag", t.getName());
    }

    @Test
    void canUpdateTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);
        Task task = getTaskById(UUID.fromString("00000000-0000-0000-0000-000000000001"));

        task.setTitle("Updated Test Task");
        databaseAdapter.updateTask(task);

        Task t = getTaskById(task.getId());

        assertEquals("Updated Test Task", t.getTitle());
    }

    @Test
    void canCreateInTrayTask(){
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        Task task = TaskFactory.createTask("Test Task", "Test Description");

        databaseAdapter.createInTrayTask(task);
        ToDoList toDoList = databaseAdapter.readToDoList();

        Task taskInInTray = toDoList.getInTrayTask(task.getId());

        UUID taskId = task.getId();
        assertThrows(NotFoundException.class, () -> toDoList.getTask(taskId));
        assertEquals(task, taskInInTray);
    }

    @Test
    void canDeleteInTrayTask(){
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(connection);

        Task task = TaskFactory.createTask("Test Task", "Test Description");

        databaseAdapter.createInTrayTask(task);
        databaseAdapter.deleteInTrayTask(task.getId());

        ToDoList toDoList = databaseAdapter.readToDoList();

        UUID taskId = task.getId();
        assertThrows(NotFoundException.class, () -> toDoList.getInTrayTask(taskId));
    }
}
