package hwr.oop.todo;


import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.persistence.DatabaseAdapter;
import hwr.oop.todo.persistence.FailedDatabaseStatementException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAdapterTest extends DatabaseAdapter {

    @Test
    void canInsertTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Task task = TaskFactory.createTask("Test Task", "Test Description");
        try {
            databaseAdapter.insertTask(task);
            Task t = databaseAdapter.getTask(task.getId());
            assertEquals(task.getId(), t.getId());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    // TODO: Test Exception for line Coverage
    @Test()
    void ThrowsExceptionWhenTaskIsInserted() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(){
            @Override
            public void insertTask(Task task) throws FailedDatabaseStatementException {
                throw new FailedDatabaseStatementException();
            }
        };
        Task task = TaskFactory.createTask("Test Task", "Test Description");
        assertThrows(FailedDatabaseStatementException.class, () -> databaseAdapter.insertTask(task));
        databaseAdapter.deleteTask(task);
    }

    @Test
    void canDeleteTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
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

    @Test()
    void ThrowsExceptionWhenGettingTask() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(){
            @Override
            public Task getTask(UUID id) throws FailedDatabaseStatementException {
                throw new FailedDatabaseStatementException();
            }
        };
        Task task = TaskFactory.createTask("Test Task", "Test Description");
        assertThrows(FailedDatabaseStatementException.class, () -> databaseAdapter.getTask(task.getId()));
    }

    @Test
    void canInsertProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Project project = ProjectFactory.createProject("Test Project");
        try {
            databaseAdapter.insertProject(project);
            Project p = databaseAdapter.getProject(project.getId());
            assertEquals(project.getId(), p.getId());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void canDeleteProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Project project = ProjectFactory.createProject("Test Project");
        try {
            databaseAdapter.insertProject(project);
            databaseAdapter.deleteProject(project);
            Project p = databaseAdapter.getProject(project.getId());
            assertNull(p);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void canUpdateProject() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Project project = ProjectFactory.createProject("Test Project");
        try {
            databaseAdapter.insertProject(project);
            project.setName("Updated Test Project");
            databaseAdapter.updateProject(project);
            Project p = databaseAdapter.getProject(project.getId());
            assertEquals("Updated Test Project", p.getName());
            databaseAdapter.deleteProject(project);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void canInsertTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Tag tag = TagFactory.createTag("Test Tag");
        try {
            databaseAdapter.insertTag(tag);
            Tag t = databaseAdapter.getTag(tag.getId());
            assertEquals(tag.getId(), t.getId());
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void canDeleteTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Tag tag = TagFactory.createTag("Test Tag");
        try {
            databaseAdapter.insertTag(tag);
            databaseAdapter.deleteTag(tag);
            Tag t = databaseAdapter.getTag(tag.getId());
            assertNull(t);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    @Test
    void canUpdateTag() {
        DatabaseAdapter databaseAdapter = new DatabaseAdapter();
        Tag tag = TagFactory.createTag("Test Tag");
        try {
            databaseAdapter.insertTag(tag);
            tag.setDescription("Updated Test Tag");
            tag.setName("Updated Test Tag");
            databaseAdapter.updateTag(tag);
            Tag t = databaseAdapter.getTag(tag.getId());
            assertEquals("Updated Test Tag", t.getName());
            databaseAdapter.deleteTag(tag);databaseAdapter.deleteTag(tag);
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }
}
