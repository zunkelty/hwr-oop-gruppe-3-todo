package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.CreateTagPort;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateTagUseCase {

    private final ToDoList toDoList;
    private final CreateTagPort createTagPort;

    public CreateTagUseCase(ToDoList toDoList, CreateTagPort createTagPort) {
        this.createTagPort = createTagPort;
        this.toDoList = toDoList;
    }

    public void insertTag(Tag tag) {
        toDoList.createTag(tag);
        createTagPort.createTag(tag);
    }

}
