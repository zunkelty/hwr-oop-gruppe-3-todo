package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.List;
import java.util.UUID;

public class GetTasksFromProjectUseCase {

    private final ToDoList toDoList;

    public GetTasksFromProjectUseCase(ToDoList toDoList) {
        this.toDoList = toDoList;
    }

    public List<Task> getTasksOfProject(UUID projectId) {
        Project project = toDoList.getProject(projectId);
        return project.getTasks();
    }

}
