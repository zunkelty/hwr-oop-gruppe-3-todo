package hwr.oop.todo.application.usecases;


import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class DeleteInTrayTaskUseCase {

    private final ToDoList toDoList;


    public DeleteInTrayTaskUseCase(ToDoList toDoList) {

        this.toDoList = toDoList;
    }

    public void deleteInTrayTask(UUID id) {
        toDoList.deleteInTrayTask(id);

    }
}
