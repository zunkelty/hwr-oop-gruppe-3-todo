package hwr.oop.todo.persistence.ports.out;

import hwr.oop.todo.library.task.Task;

interface InsertTaskOutPort {
    void insertTask(Task task);
}
