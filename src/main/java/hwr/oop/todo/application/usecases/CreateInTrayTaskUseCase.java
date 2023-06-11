package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.CreateInTrayTaskPort;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateInTrayTaskUseCase {

    private final ToDoList toDoList;
    private final CreateInTrayTaskPort createInTrayTaskPort;

    public CreateInTrayTaskUseCase(ToDoList toDoList, CreateInTrayTaskPort createInTrayTaskPort) {
        this.toDoList = toDoList;
        this.createInTrayTaskPort = createInTrayTaskPort;
    }

    public void insertInTrayTask(Task task) {
        toDoList.createInTrayTask(task);
        createInTrayTaskPort.createInTrayTask(task);
    }
}
