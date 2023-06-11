package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.UpdateTaskPort;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class UpdateTaskUseCase {

    private final ToDoList toDoList;
    private final UpdateTaskPort updateTaskPort;

    public UpdateTaskUseCase(ToDoList toDoList, UpdateTaskPort updateTaskPort) {
        this.toDoList = toDoList;
        this.updateTaskPort = updateTaskPort;
    }

    public Task editTask(Task task) {
        updateTaskPort.updateTask(task);
        return toDoList.editTask(task);
    }
}
