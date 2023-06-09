package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.persistence.Persistence;

public class UseCases {
    CreateTaskUseCase createTaskUseCase;
    GetTaskUseCase getTaskUseCase;
    CreateProjectUseCase createProjectUseCase;
    GetProjectUseCase getProjectUseCase;
    AddTaskToProjectUseCase addTaskToProjectUseCase;
    GetTasksFromProjectUseCase getTasksFromProjectUseCase;
    CreateTagUseCase createTagUseCase;
    GetTagUseCase getTagUseCase;
    AddTagToTaskUseCase addTagToTaskUseCase;


    public static UseCases initialize(Persistence persistence) {
        ToDoList toDoList = persistence.loadToDoList();

        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(toDoList, persistence);
        GetTaskUseCase getTaskUseCase = new GetTaskUseCase(toDoList);
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(toDoList, persistence);
        GetProjectUseCase getProjectUseCase = new GetProjectUseCase(toDoList);
        AddTaskToProjectUseCase addTaskToProjectUseCase = new AddTaskToProjectUseCase(toDoList, persistence);
        GetTasksFromProjectUseCase getTasksFromProjectUseCase = new GetTasksFromProjectUseCase(toDoList);
        CreateTagUseCase createTagUseCase = new CreateTagUseCase(toDoList, persistence);
        GetTagUseCase getTagUseCase = new GetTagUseCase(toDoList);
        AddTagToTaskUseCase addTagToTaskUseCase = new AddTagToTaskUseCase(toDoList, persistence);

        return new UseCases(createTaskUseCase, getTaskUseCase, createProjectUseCase, getProjectUseCase, addTaskToProjectUseCase, getTasksFromProjectUseCase, createTagUseCase, getTagUseCase, addTagToTaskUseCase);
    }

    public UseCases(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase, CreateProjectUseCase createProjectUseCase, GetProjectUseCase getProjectUseCase, AddTaskToProjectUseCase addTaskToProjectUseCase, GetTasksFromProjectUseCase getTasksFromProjectUseCase, CreateTagUseCase createTagUseCase, GetTagUseCase getTagUseCase, AddTagToTaskUseCase addTagToTaskUseCase){
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.createProjectUseCase = createProjectUseCase;
        this.getProjectUseCase = getProjectUseCase;
        this.addTaskToProjectUseCase = addTaskToProjectUseCase;
        this.getTasksFromProjectUseCase = getTasksFromProjectUseCase;
        this.createTagUseCase = createTagUseCase;
        this.getTagUseCase = getTagUseCase;
        this.addTagToTaskUseCase = addTagToTaskUseCase;
    }

    public CreateTaskUseCase getCreateTaskUseCase() {
        return createTaskUseCase;
    }

    public GetTaskUseCase getTaskUseCase(){
        return getTaskUseCase;
    }

    public CreateProjectUseCase getCreateProjectUseCase() {
        return createProjectUseCase;
    }

    public GetProjectUseCase getProjectUseCase() {
        return getProjectUseCase;
    }

    public AddTaskToProjectUseCase getAddTaskToProjectUseCase() {
        return addTaskToProjectUseCase;
    }

    public GetTasksFromProjectUseCase getTasksFromProjectUseCase() {
        return getTasksFromProjectUseCase;
    }

    public CreateTagUseCase getCreateTagUseCase() {
        return createTagUseCase;
    }

    public GetTagUseCase getTagUseCase() {
        return getTagUseCase;
    }

    public AddTagToTaskUseCase getAddTagToTaskUseCase() {
        return addTagToTaskUseCase;
    }
}
