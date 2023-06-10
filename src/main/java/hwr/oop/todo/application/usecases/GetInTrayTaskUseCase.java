package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class GetInTrayTaskUseCase {
    private final ToDoList toDoList;

    public GetInTrayTaskUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Task getInTrayTaskById(UUID id) {
        return toDoList.getInTrayTask(id);
    }
}
