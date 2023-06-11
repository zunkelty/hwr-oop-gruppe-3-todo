package hwr.oop.todo.application.ports;

import hwr.oop.todo.library.task.Task;

public interface CreateTaskPort {
    void createTask(Task task);
}
