package hwr.oop.todo.application.ports;

import hwr.oop.todo.library.todolist.ToDoList;

public interface LoadToDoListPort {
    ToDoList loadToDoList();
}
