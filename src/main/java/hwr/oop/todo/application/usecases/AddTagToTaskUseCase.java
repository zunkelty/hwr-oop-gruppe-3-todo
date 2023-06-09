package hwr.oop.todo.application.usecases;

import hwr.oop.todo.application.ports.UpdateTaskPort;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

import java.util.UUID;

public class AddTagToTaskUseCase {

    private final ToDoList toDoList;
    private final UpdateTaskPort updateTaskPort;

    public AddTagToTaskUseCase(ToDoList toDoList, UpdateTaskPort updateTaskPort) {
        this.updateTaskPort = updateTaskPort;
        this.toDoList = toDoList;
    }

    public void addTagToTask(UUID taskId, UUID tagId) {
        Tag tag = toDoList.getTag(tagId);
        Task task = toDoList.getTask(taskId);

        task.addTag(tag);
        updateTaskPort.updateTask(task);
    }

}
