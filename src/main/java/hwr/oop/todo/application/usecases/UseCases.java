package hwr.oop.todo.application.usecases;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.persistence.Persistence;

public class UseCases {
    CreateTaskUseCase createTaskUseCase;
    GetTaskUseCase getTaskUseCase;

    CreateInTrayTaskUseCase createInTrayTaskUseCase;
    GetInTrayTaskUseCase getInTrayTaskUseCase;
    DeleteInTrayTaskUseCase deleteInTrayTaskUseCase;
    CreateProjectUseCase createProjectUseCase;
    GetProjectUseCase getProjectUseCase;
    AddTaskToProjectUseCase addTaskToProjectUseCase;
    GetTasksFromProjectUseCase getTasksFromProjectUseCase;
    CreateTagUseCase createTagUseCase;
    GetTagUseCase getTagUseCase;
    AddTagToTaskUseCase addTagToTaskUseCase;
    GetOpenTasksUseCase getOpenTasksUseCase;
    UpdateTaskUseCase updateTaskUseCase;


    public static UseCases initialize(Persistence persistence) {
        ToDoList toDoList = persistence.loadToDoList();

        CreateTaskUseCase createTaskUseCase = new CreateTaskUseCase(toDoList, persistence);
        GetTaskUseCase getTaskUseCase = new GetTaskUseCase(toDoList);
        CreateInTrayTaskUseCase createInTrayTaskUseCase = new CreateInTrayTaskUseCase(toDoList);
        GetInTrayTaskUseCase getInTrayTaskUseCase = new GetInTrayTaskUseCase (toDoList);
        DeleteInTrayTaskUseCase deleteInTrayTaskUseCase = new DeleteInTrayTaskUseCase(toDoList);
        CreateProjectUseCase createProjectUseCase = new CreateProjectUseCase(toDoList, persistence);
        GetProjectUseCase getProjectUseCase = new GetProjectUseCase(toDoList);
        AddTaskToProjectUseCase addTaskToProjectUseCase = new AddTaskToProjectUseCase(toDoList, persistence);
        GetTasksFromProjectUseCase getTasksFromProjectUseCase = new GetTasksFromProjectUseCase(toDoList);
        CreateTagUseCase createTagUseCase = new CreateTagUseCase(toDoList, persistence);
        GetTagUseCase getTagUseCase = new GetTagUseCase(toDoList);
        AddTagToTaskUseCase addTagToTaskUseCase = new AddTagToTaskUseCase(toDoList, persistence);
        GetOpenTasksUseCase getOpenTasksUseCase = new GetOpenTasksUseCase(toDoList);
        UpdateTaskUseCase updateTaskUseCase = new UpdateTaskUseCase(toDoList, persistence);

        return new UseCases(createTaskUseCase, getTaskUseCase, createInTrayTaskUseCase, getInTrayTaskUseCase, deleteInTrayTaskUseCase, createProjectUseCase, getProjectUseCase, addTaskToProjectUseCase, getTasksFromProjectUseCase, createTagUseCase, getTagUseCase, addTagToTaskUseCase, getOpenTasksUseCase);
    }

    public UseCases(CreateTaskUseCase createTaskUseCase, GetTaskUseCase getTaskUseCase,CreateInTrayTaskUseCase createInTrayTaskUseCase, GetInTrayTaskUseCase getInTrayTaskUseCase, DeleteInTrayTaskUseCase deleteInTrayTaskUseCase, CreateProjectUseCase createProjectUseCase, GetProjectUseCase getProjectUseCase, AddTaskToProjectUseCase addTaskToProjectUseCase, GetTasksFromProjectUseCase getTasksFromProjectUseCase, CreateTagUseCase createTagUseCase, GetTagUseCase getTagUseCase, AddTagToTaskUseCase addTagToTaskUseCase, GetOpenTasksUseCase getOpenTasksUseCase){
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.createInTrayTaskUseCase = createInTrayTaskUseCase;
        this.deleteInTrayTaskUseCase = deleteInTrayTaskUseCase;
        this.getInTrayTaskUseCase = getInTrayTaskUseCase;
        this.createProjectUseCase = createProjectUseCase;
        this.getProjectUseCase = getProjectUseCase;
        this.addTaskToProjectUseCase = addTaskToProjectUseCase;
        this.getTasksFromProjectUseCase = getTasksFromProjectUseCase;
        this.createTagUseCase = createTagUseCase;
        this.getTagUseCase = getTagUseCase;
        this.addTagToTaskUseCase = addTagToTaskUseCase;
        this.getOpenTasksUseCase = getOpenTasksUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
    }

    public CreateTaskUseCase getCreateTaskUseCase() {
        return createTaskUseCase;
    }

    public GetTaskUseCase getTaskUseCase(){
        return getTaskUseCase;
    }

    public CreateInTrayTaskUseCase getCreateInTrayTaskUseCase() {
        return createInTrayTaskUseCase;
    }

    public GetInTrayTaskUseCase getInTrayTaskUseCase(){
        return getInTrayTaskUseCase;
    }

    public DeleteInTrayTaskUseCase deleteInTrayTaskUseCase(){
        return deleteInTrayTaskUseCase;
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

    public GetOpenTasksUseCase getOpenTasksUseCase() {
        return getOpenTasksUseCase;
    }

    public UpdateTaskUseCase getEditTaskUseCase() {
        return updateTaskUseCase;
    }
}
