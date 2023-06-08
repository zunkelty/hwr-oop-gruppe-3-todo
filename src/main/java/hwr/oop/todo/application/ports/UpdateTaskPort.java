package hwr.oop.todo.application.ports;

import hwr.oop.todo.library.task.Task;

import java.util.UUID;

public interface UpdateTaskPort {
    void updateTask(Task task);
}
