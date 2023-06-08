package hwr.oop.todo;


import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.persistence.DatabaseAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAdapterTest {
    private static DatabaseAdapter databaseAdapter;

    @BeforeAll
    static void setUp() {
        databaseAdapter = new DatabaseAdapter();
    }

    @Test
    void canInsertTask() {
        Task task = TaskFactory.createTask("Test Task", "Test Description");

        try {
            databaseAdapter.insertTask(task);
            Task t = databaseAdapter.getTask(task.getId());
            assertEquals(task.getId(), t.getId());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void canDeleteTask() {
        // Create a test Task object
        Task task = TaskFactory.createTask( "Test Task", "Test Description");
        try {
            databaseAdapter.insertTask(task);
            databaseAdapter.deleteTask(task);
            Task t = databaseAdapter.getTask(task.getId());
            assertNull(t);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
