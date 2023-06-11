package hwr.oop.todo.library.project;

import hwr.oop.todo.library.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Project {
    private final UUID id;
    private String name;
    private final List<Task> tasks = new ArrayList<>();

    public Project(UUID id, String name){
        this.name = name;
        this.id = id;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public List<Task> getTasks(){
        return tasks;
    }

    public String getName(){
        return name;
    }

    public UUID getId(){
        return id;
    }

    @Override
    @SuppressWarnings("java:S1206")
    public int hashCode() {
        return Objects.hash(id, name);
    }


    public void setName(String name) {
        this.name = name;
    }
}
