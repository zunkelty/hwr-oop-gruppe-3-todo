package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.InsertTaskPort;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateTaskUseCase {

    private final ToDoList toDoList;
    private final InsertTaskPort insertTaskPort;

    public CreateTaskUseCase(ToDoList toDoList, InsertTaskPort insertTaskPort) {
        this.insertTaskPort = insertTaskPort;
        this.toDoList = toDoList;
    }

    public void insertTask(Task task) {
        toDoList.addTask(task);
        insertTaskPort.insertTask(task);
    }
}
