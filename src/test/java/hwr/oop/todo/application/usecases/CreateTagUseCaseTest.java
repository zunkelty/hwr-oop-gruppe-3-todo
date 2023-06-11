package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.*;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;
import hwr.oop.todo.library.todolist.ToDoList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateTagUseCaseTest {

    @Test
    void createTag() {
        CreateTagPort port = mock(CreateTagPort.class);
        ToDoList toDoList = new ToDoList();

        CreateTagUseCase useCase = new CreateTagUseCase(toDoList, port);

        Tag tag = TagFactory.createTag("Test Tag");
        useCase.insertTag(tag);

        assertNotNull(toDoList.getTag(tag.getId()));
        verify(port, times(1)).createTag(tag);
    }
}
