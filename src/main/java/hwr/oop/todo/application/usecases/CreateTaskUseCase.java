package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.CreateTaskPort;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateTaskUseCase {

    private final ToDoList toDoList;
    private final CreateTaskPort createTaskPort;

    public CreateTaskUseCase(ToDoList toDoList, CreateTaskPort createTaskPort) {
        this.createTaskPort = createTaskPort;
        this.toDoList = toDoList;
    }

    public void insertTask(Task task) {
        toDoList.createTask(task);
        createTaskPort.createTask(task);
    }
}
