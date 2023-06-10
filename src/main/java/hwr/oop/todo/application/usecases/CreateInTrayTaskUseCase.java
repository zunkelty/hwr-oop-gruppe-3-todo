package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateInTrayTaskUseCase {

    private final ToDoList toDoList;

    public CreateInTrayTaskUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public void insertInTrayTask(Task task) {
        toDoList.createInTrayTask(task);
    }
}
