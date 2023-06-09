package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.InsertProjectPort;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateProjectUseCase {

    private final ToDoList toDoList;
    private final InsertProjectPort insertProjectPort;

    public CreateProjectUseCase(ToDoList toDoList, InsertProjectPort insertProjectPort) {
        this.toDoList = toDoList;
        this.insertProjectPort = insertProjectPort;
    }

    public void createProject(Project project) {
        toDoList.createProject(project);
        insertProjectPort.insertProject(project);
    }

}
