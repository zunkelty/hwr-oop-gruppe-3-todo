package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.*;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateTaskUseCaseTest {

    @Test
    void createTask() {
        CreateTaskPort port = mock(CreateTaskPort.class);
        ToDoList toDoList = new ToDoList();

        CreateTaskUseCase useCase = new CreateTaskUseCase(toDoList, port);

        Task task = TaskFactory.createTask("Test Task");
        useCase.insertTask(task);

        assertNotNull(toDoList.getTask(task.getId()));
        verify(port, times(1)).createTask(task);
    }
}
