package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.UpdateTaskPort;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.task.TaskFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AddTagToTaskUseCaseTest {

    @Test
    void addsTag() {
        UpdateTaskPort port = mock(UpdateTaskPort.class);
        ToDoList toDoList = new ToDoList();

        AddTagToTaskUseCase useCase = new AddTagToTaskUseCase(toDoList, port);

        Task task = TaskFactory.createTask("Test Task");
        toDoList.createTask(task);

        Tag tag = TagFactory.createTag("Tag");
        toDoList.createTag(tag);

        useCase.addTagToTask(task.getId(), tag.getId());

        assertTrue(task.getTags().contains(tag));
        verify(port, times(1)).updateTask(task);
    }
}
