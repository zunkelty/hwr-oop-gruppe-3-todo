package hwr.oop.todo.library;

import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.DuplicateIdException;
import hwr.oop.todo.library.todolist.NotFoundException;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.tag.Tag;
import java.util.UUID;

class ToDoListTest {
    private ToDoList toDoList;

    @BeforeEach
    void setUp() {
        toDoList = new ToDoList();

    }

    @Test
    void canAddTask() {
        Task task = TaskFactory.createTask("Example Task");
        toDoList.createTask(task);

        Task retrievedTask = toDoList.getTask(task.getId());
        assertNotNull(retrievedTask);
        assertEquals(task.getId(), retrievedTask.getId());
        assertEquals(task.getTitle(), retrievedTask.getTitle());
    }

    @Test
    void addingTaskWithDuplicateIdThrowsException() {
        Task task = TaskFactory.createTask("Example Task");
        toDoList.createTask(task);

        // Adding a task with the same ID should throw DuplicateIdException
        assertThrows(DuplicateIdException.class, () -> toDoList.createTask(task));
    }

    @Test
    void gettingTaskWithInvalidIdThrowsException() {
        UUID invalidId = UUID.randomUUID();

        // Getting a task with an invalid ID should throw NotFoundException
        assertThrows(NotFoundException.class, () -> toDoList.getTask(invalidId));
    }

    @Test
    void canAddInTrayTask() {
        Task task = TaskFactory.createTask("Example Task");
        toDoList.createInTrayTask(task);

        Task retrievedTask = toDoList.getInTrayTask(task.getId());
        assertNotNull(retrievedTask);
        assertEquals(task.getId(), retrievedTask.getId());
        assertEquals(task.getTitle(), retrievedTask.getTitle());
    }

    @Test
    void addingInTrayTaskWithDuplicateIdThrowsException() {
        Task task = TaskFactory.createTask("Example Task");
        toDoList.createInTrayTask(task);

        // Adding a task with the same ID should throw DuplicateIdException
        assertThrows(DuplicateIdException.class, () -> toDoList.createInTrayTask(task));
    }

    @Test
    void gettingInTrayTaskWithInvalidIdThrowsException() {
        UUID invalidId = UUID.randomUUID();

        // Getting a task with an invalid ID should throw NotFoundException
        assertThrows(NotFoundException.class, () -> toDoList.getInTrayTask(invalidId));
    }
    @Test
    void canDeleteInTrayTask() {
        Task task = TaskFactory.createTask("Example Task");
        toDoList.createInTrayTask(task);

        UUID TestID = task.getId();

        toDoList.deleteInTrayTask(TestID);

        assertThrows(NotFoundException.class, () -> {
            toDoList.getInTrayTask(TestID);
        });
    }

    @Test
    void deletingInTrayTaskWithInvalidIdThrowsException() {
        UUID invalidId = UUID.randomUUID();

        // Getting a task with an invalid ID should throw NotFoundException
        assertThrows(NotFoundException.class, () -> toDoList.deleteInTrayTask(invalidId));
    }

    @Test
    void canCreateProject() {
        Project project = ProjectFactory.createProject("Example Project");
        toDoList.createProject(project);

        Project retrievedProject = toDoList.getProject(project.getId());
        assertNotNull(retrievedProject);
        assertEquals(project.getId(), retrievedProject.getId());
        assertEquals(project.getName(), retrievedProject.getName());
    }

    @Test
    void creatingProjectWithDuplicateIdThrowsException() {
        Project project = ProjectFactory.createProject("Example Project");
        toDoList.createProject(project);

        // Creating a project with the same ID should throw DuplicateIdException
        assertThrows(DuplicateIdException.class, () -> toDoList.createProject(project));
    }

    @Test
    void gettingProjectWithInvalidIdThrowsException() {
        UUID invalidId = UUID.randomUUID();

        // Getting a project with an invalid ID should throw NotFoundException
        assertThrows(NotFoundException.class, () -> toDoList.getProject(invalidId));
    }

    @Test
    void canCreateTag() {
        Tag tag = TagFactory.createTag("Example Tag");
        toDoList.createTag(tag);

        Tag retrievedTag = toDoList.getTag(tag.getId());
        assertNotNull(retrievedTag);
        assertEquals(tag.getId(), retrievedTag.getId());
        assertEquals(tag.getName(), retrievedTag.getName());
    }

    @Test
    void creatingTagWithDuplicateIdThrowsException() {
        Tag tag = TagFactory.createTag("Example Tag");
        toDoList.createTag(tag);

        // Creating a tag with the same ID should throw DuplicateIdException
        assertThrows(DuplicateIdException.class, () -> toDoList.createTag(tag));
    }

    @Test
    void gettingTagWithInvalidIdThrowsException() {
        UUID invalidId = UUID.randomUUID();

        // Getting a tag with an invalid ID should throw NotFoundException
        assertThrows(NotFoundException.class, () -> toDoList.getTag(invalidId));
    }


    @Test
    void testEditNonExistingTask() {
        UUID taskId = UUID.randomUUID();
        Task task = new Task(taskId, "Testaufgabe", "Beschreibung");

        // Überprüfen, ob eine NotFoundException ausgelöst wird
       assertThrows(NotFoundException.class, () -> toDoList.editTask(task));
    }

}
