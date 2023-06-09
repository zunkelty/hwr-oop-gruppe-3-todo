package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.UpdateProjectPort;
import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class AddTaskToProjectUseCase {

    private final ToDoList toDoList;
    private final UpdateProjectPort updateProjectPort;

    public AddTaskToProjectUseCase(ToDoList toDoList, UpdateProjectPort updateProjectPort) {
        this.updateProjectPort = updateProjectPort;
        this.toDoList = toDoList;
    }

    public void addTaskToProject(UUID projectId, UUID taskId) {
        Project project = toDoList.getProject(projectId);
        Task task = toDoList.getTask(taskId);

        project.addTask(task);
        updateProjectPort.updateProject(project);
    }

}
