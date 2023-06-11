package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.*;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.NotFoundException;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeleteInTrayTaskUseCaseTest {

    @Test
    void deleteInTrayTask() {
        DeleteInTrayTaskPort port = mock(DeleteInTrayTaskPort.class);
        ToDoList toDoList = new ToDoList();

        DeleteInTrayTaskUseCase useCase = new DeleteInTrayTaskUseCase(toDoList, port);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.createInTrayTask(task);

        useCase.deleteInTrayTask(task.getId());

        UUID taskId = task.getId();
        assertThrows(NotFoundException.class, () -> toDoList.getInTrayTask(taskId));
        verify(port, times(1)).deleteInTrayTask(task.getId());
    }
}
