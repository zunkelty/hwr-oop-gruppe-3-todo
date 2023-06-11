package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.*;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.task.TaskState;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UpdateTaskUseCaseTest {

    @Test
    void updateTask() {

        UpdateTaskPort port = mock(UpdateTaskPort.class);
        ToDoList toDoList = new ToDoList();

        UpdateTaskUseCase useCase = new UpdateTaskUseCase(toDoList, port);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.createTask(task);

        task.setTitle("New Title");
        task.setState(TaskState.DONE);

        Task readTask = useCase.updateTask(task);

        assertNotNull(readTask);
        assertEquals(task.getTitle(), readTask.getTitle());
        assertEquals(task.getState(), readTask.getState());
        verify(port, times(1)).updateTask(task);
    }
}
