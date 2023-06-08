package hwr.oop.todo.persistence.ports.out;

import hwr.oop.todo.library.task.Task;

public interface InsertTaskOutPort {
    void insertTask(Task task);
}
