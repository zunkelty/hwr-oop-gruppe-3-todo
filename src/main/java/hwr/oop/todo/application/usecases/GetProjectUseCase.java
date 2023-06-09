package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class GetProjectUseCase {

    private final ToDoList toDoList;

    public GetProjectUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public Project getProjectById(UUID id) {
        return toDoList.getProject(id);
    }

}
