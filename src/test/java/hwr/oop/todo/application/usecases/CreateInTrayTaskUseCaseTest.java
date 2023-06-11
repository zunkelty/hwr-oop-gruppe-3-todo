package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.CreateInTrayTaskPort;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateInTrayTaskUseCaseTest {

    @Test
    void addTaskToInTray() {
        CreateInTrayTaskPort port = mock(CreateInTrayTaskPort.class);
        ToDoList toDoList = new ToDoList();

        CreateInTrayTaskUseCase useCase = new CreateInTrayTaskUseCase(toDoList, port);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.createTask(task);

        useCase.insertInTrayTask(task);

        assertNotNull(toDoList.getInTrayTask(task.getId()));
        verify(port, times(1)).createInTrayTask(task);
    }
}
