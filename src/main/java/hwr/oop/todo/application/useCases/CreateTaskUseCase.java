package hwr.oop.todo.application.useCases;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.persistence.ports.out.InsertProjectOutPort;
import hwr.oop.todo.persistence.ports.out.InsertTaskOutPort;

public class CreateTaskUseCase {

    private final ToDoList toDoList;
    private final InsertTaskOutPort insertTaskOutPort;

    public CreateTaskUseCase(ToDoList toDoList, InsertTaskOutPort insertTaskOutPort) {
        this.insertTaskOutPort = insertTaskOutPort;
        this.toDoList = toDoList;
    }

    public void insertTask(Task task) {
        toDoList.addTask(task);
        insertTaskOutPort.insertTask(task);
    }
}
