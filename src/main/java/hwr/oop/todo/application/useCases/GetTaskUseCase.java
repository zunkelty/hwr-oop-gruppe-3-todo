package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class GetTaskUseCase {
    private final ToDoList toDoList;

    public GetTaskUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Task getTaskById(UUID id) {
        return toDoList.getTask(id);
    }
}
