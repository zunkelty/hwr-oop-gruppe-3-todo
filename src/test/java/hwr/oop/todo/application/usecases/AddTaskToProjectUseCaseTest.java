package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.UpdateProjectPort;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddTaskToProjectUseCaseTest {

    @Test
    void addsTaskToProject() {
        UpdateProjectPort port = mock(UpdateProjectPort.class);
        ToDoList toDoList = new ToDoList();

        AddTaskToProjectUseCase useCase = new AddTaskToProjectUseCase(toDoList, port);

        Project project = ProjectFactory.createProject("Test Project");
        toDoList.createProject(project);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.createTask(task);

        useCase.addTaskToProject(project.getId(), task.getId());

        assertTrue(project.getTasks().contains(task));
        verify(port, times(1)).updateProject(project);
    }
}
