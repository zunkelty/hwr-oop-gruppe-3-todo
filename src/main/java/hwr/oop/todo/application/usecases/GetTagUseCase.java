package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class GetTagUseCase {

    private final ToDoList toDoList;

    public GetTagUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Tag getTagById(UUID id) {
        return toDoList.getTag(id);
    }
}
