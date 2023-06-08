package hwr.oop.todo.library.task;

import java.util.UUID;

public class TaskFactory {
    public static Task createTask(String title, String description){
        UUID id = UUID.randomUUID();
        return new Task(id, title, description);
    }

    public static Task createTask(String title){
        UUID id = UUID.randomUUID();
        return new Task(id, title);
    }

    // create task with state, id, title, description
    public static Task createTask(TaskState state, UUID id, String title, String description){
        Task task = new Task(id, title, description);
        task.setState(state);
        return task;
    }

    private TaskFactory() {
    }
}
