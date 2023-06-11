package hwr.oop.todo.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import java.util.List;
import java.util.UUID;

class ProjectTest {

    @Test
    void canGetName() {
        String projectName = "Example Project";
        Project project = new Project(UUID.randomUUID(), projectName);

        assertEquals(projectName, project.getName());
    }

    @Test
    void canAddTask() {
        String projectName = "Example Project";
        Project project = new Project(UUID.randomUUID(), projectName);

        Task task = TaskFactory.createTask("Example Task");
        project.addTask(task);

        List<Task> tasks = project.getTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains(task));
    }

    @Test
    void canGetId() {
        String projectName = "Example Project";
        UUID projectId = UUID.randomUUID();
        Project project = new Project(projectId, projectName);

        assertEquals(projectId, project.getId());
    }

    @Test
    void canCompareEquality() {
        String projectName1 = "Project 1";
        UUID projectId1 = UUID.randomUUID();
        UUID projectId2 = UUID.randomUUID();
        Project project1 = new Project(projectId1, projectName1);
        Task task = TaskFactory.createTask("Task 1");

        String projectName2 = "Project 1";
        Project project2 = new Project(projectId2, projectName2);

        project1.addTask(task);
        project2.addTask(task);

        assertNotEquals(project1, project2);
    }

    @Test
    void canCompareEqualityWithSameId() {
        String projectName = "Example Project";
        UUID projectId = UUID.randomUUID();
        Project project1 = new Project(projectId, projectName);
        Project project2 = new Project(projectId, projectName);

        assertEquals(project1.hashCode(), project2.hashCode());

        project2.setName("Project Name");

        assertNotEquals(project1.hashCode(), project2.hashCode());
    }

    @Test
    void canSetName() {
        String projectName = "Example Project";
        UUID projectId = UUID.randomUUID();
        Project project = new Project(projectId, projectName);

        String newName = "New Project Name";
        project.setName(newName);

        assertEquals(newName, project.getName());
    }

    @Test
    void canCompareEqualityWithSameNameAndTasks() {
        String projectName = "Example Project";
        UUID projectId1 = UUID.randomUUID();
        UUID projectId2 = UUID.randomUUID();
        Task task1 = TaskFactory.createTask("Task 1");
        Task task2 = TaskFactory.createTask("Task 2");

        Project project1 = new Project(projectId1, projectName);
        Project project2 = new Project(projectId2, projectName);

        project1.addTask(task1);
        project2.addTask(task2);

        assertNotEquals(project1, project2);
    }

    @Test
    void canCompareEqualityWithNull() {
        String projectName = "Example Project";
        UUID projectId = UUID.randomUUID();
        Project project = new Project(projectId, projectName);

        assertNotNull(project);
    }

    @Test
    void canCompareEqualityWithDifferentObject() {
        String projectName = "Example Project";
        UUID projectId = UUID.randomUUID();
        Project project = new Project(projectId, projectName);

        assertNotEquals(project, new Object());
    }

    @Test
    void canCompareEqualityWithSameObject() {
        String projectName = "Example Project";
        UUID projectId = UUID.randomUUID();
        Project project = new Project(projectId, projectName);

        assertEquals(project, project);
    }
}
