package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.List;

public class GetOpenTasksUseCase {
    private final ToDoList toDoList;

    public GetOpenTasksUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public List<Task> getOpenTasks() {
        return toDoList.getOpenTasks();
    }
}
