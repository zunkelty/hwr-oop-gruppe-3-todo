package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.CreateProjectPort;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.todolist.ToDoList;

public class CreateProjectUseCase {

    private final ToDoList toDoList;
    private final CreateProjectPort createProjectPort;

    public CreateProjectUseCase(ToDoList toDoList, CreateProjectPort createProjectPort) {
        this.toDoList = toDoList;
        this.createProjectPort = createProjectPort;
    }

    public void createProject(Project project) {
        toDoList.createProject(project);
        createProjectPort.createProject(project);
    }

}
