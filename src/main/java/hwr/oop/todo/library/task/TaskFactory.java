package hwr.oop.todo.library.task;

import java.util.UUID;

public class TaskFactory {

    private TaskFactory(){
        throw new IllegalStateException("Utility class");
    }

    public static Task createTask(String title, String description){
        UUID id = UUID.randomUUID();
        return new Task(id, title, description);
    }

    public static Task createTask(String title){
        UUID id = UUID.randomUUID();
        return new Task(id, title);
    }
}
