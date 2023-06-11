package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.CreateProjectPort;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.project.ProjectFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateProjectUseCaseTest {

    @Test
    void createProject() {
        CreateProjectPort port = mock(CreateProjectPort.class);
        ToDoList toDoList = new ToDoList();

        CreateProjectUseCase useCase = new CreateProjectUseCase(toDoList, port);

        Project project = ProjectFactory.createProject("Test Project");
        useCase.createProject(project);

        assertNotNull(toDoList.getProject(project.getId()));
        verify(port, times(1)).createProject(project);
    }
}
