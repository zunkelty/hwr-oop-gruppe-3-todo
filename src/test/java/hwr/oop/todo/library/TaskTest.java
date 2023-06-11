package hwr.oop.todo.library;

import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;

class TaskTest {

    @Test
    void canGetTitle() {
        String title = "Example Task";
        Task task = TaskFactory.createTask(title);

        assertEquals(title, task.getTitle());
    }

    @Test
    void canGetDescription() {
        String title = "Example Task";
        String description = "Task Description";
        Task task = TaskFactory.createTask(title, description);

        assertEquals(description, task.getDescription());
    }

    @Test
    void canGetTags() {
        String title = "Example Task";
        Task task = TaskFactory.createTask(title);

        assertTrue(task.getTags().isEmpty());

        Tag tag = TagFactory.createTag("Example Tag");
        task.addTag(tag);

        assertEquals(1, task.getTags().size());
        assertTrue(task.getTags().contains(tag));
    }

    @Test
    void canAddTag() {
        String title = "Example Task";
        Task task = TaskFactory.createTask(title);

        Tag tag = TagFactory.createTag("Example Tag");
        task.addTag(tag);

        assertEquals(1, task.getTags().size());
        assertTrue(task.getTags().contains(tag));
    }

    @Test
    void canRemoveTag() {
        String title = "Example Task";
        Task task = TaskFactory.createTask(title);

        Tag tag = TagFactory.createTag("Example Tag");
        task.addTag(tag);

        assertEquals(1, task.getTags().size());
        assertTrue(task.getTags().contains(tag));

        task.removeTag(tag);

        assertTrue(task.getTags().isEmpty());
    }

    @Test
    void canGetId() {
        String title = "Example Task";
        Task task = TaskFactory.createTask(title);

        assertNotNull(task.getId());
    }


    @Test
    void canCompareTasks() {
        String title = "Example Task";
        Task task1 = TaskFactory.createTask(title);
        Task task2 = TaskFactory.createTask(title);

        assertNotEquals(task1, task2);
    }

    @Test
    void canCompareTasksHashCode() {
        String title = "Example Task";
        Task task1 = TaskFactory.createTask(title);
        Task task2 = TaskFactory.createTask(title);

        assertNotEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void canCreateTaskWithInitialState() {
        String title = "Example Task";
        Task task1 = TaskFactory.createTask(title);

        assertEquals(TaskState.OPEN, task1.getState());
    }

    @Test
    void canSetState() {
        String title = "Example Task";
        Task task1 = TaskFactory.createTask(title);

        task1.setState(TaskState.IN_PROGRESS);
        assertEquals(TaskState.IN_PROGRESS, task1.getState());

        task1.setState(TaskState.DONE);
        assertEquals(TaskState.DONE, task1.getState());
    }

    @Test
    void canSetDescription() {
        String title = "Example Task";
        Task task1 = TaskFactory.createTask(title);

        task1.setDescription("New description");

        assertEquals("New description", task1.getDescription());

    }

}
