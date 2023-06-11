package hwr.oop.todo.application.usecases;


import hwr.oop.todo.application.ports.DeleteInTrayTaskPort;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class DeleteInTrayTaskUseCase {

    private final ToDoList toDoList;
    private final DeleteInTrayTaskPort deleteInTrayTaskPort;

    public DeleteInTrayTaskUseCase(ToDoList toDoList, DeleteInTrayTaskPort deleteInTrayTaskPort) {
        this.deleteInTrayTaskPort = deleteInTrayTaskPort;
        this.toDoList = toDoList;
    }

    public void deleteInTrayTask(UUID id) {
        toDoList.deleteInTrayTask(id);
        deleteInTrayTaskPort.deleteInTrayTask(id);
    }
}
