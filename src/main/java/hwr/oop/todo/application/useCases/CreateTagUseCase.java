package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.InsertTagPort;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateTagUseCase {

    private final ToDoList toDoList;
    private final InsertTagPort insertTagPort;

    public CreateTagUseCase(ToDoList toDoList, InsertTagPort insertTagPort) {
        this.insertTagPort = insertTagPort;
        this.toDoList = toDoList;
    }

    public void insertTag(Tag tag) {
        toDoList.createTag(tag);
        insertTagPort.insertTag(tag);
    }

}
