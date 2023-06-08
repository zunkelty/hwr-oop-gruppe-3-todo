package hwr.oop.todo.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import java.util.List;

class ProjectTest {

    @Test
    void canGetName() {
        String projectName = "Example Project";
        Project project = ProjectFactory.createProject(projectName);

        assertEquals(projectName, project.getName());
    }

    @Test
    void canAddTask() {
        String projectName = "Example Project";
        Project project = ProjectFactory.createProject(projectName);

        Task task = TaskFactory.createTask("Example Task");
        project.addTask(task);

        List<Task> tasks = project.getTasks();
        assertEquals(1, tasks.size());
        assertTrue(tasks.contains(task));
    }

    @Test
    void canGetId() {
        String projectName = "Example Project";
        Project project = ProjectFactory.createProject(projectName);

        assertNotNull(project.getId());
    }

    @Test
    void canCompareEquality() {
        String projectName1 = "Project 1";
        Project project1 = ProjectFactory.createProject(projectName1);

        String projectName2 = "Project 2";
        Project project2 = ProjectFactory.createProject(projectName2);

        assertNotEquals(project1, project2);
    }

    @Test
    void canCompareEqualityWithSameId() {
        String projectName = "Example Project";
        Project project1 = ProjectFactory.createProject(projectName);
        Project project2 = ProjectFactory.createProject(projectName);

        assertNotEquals(project1.hashCode(), project2.hashCode());
    }
}
