package hwr.oop.todo.library.project;

import hwr.oop.todo.library.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Project {
    private final UUID id;
    private final String name;
    private final List<Task> tasks = new ArrayList<>();

    Project(UUID id, String name){
        this.name = name;
        this.id = id;
    }

    public void addTask(Task task){
        tasks.add(task);
    }

    public Project(UUID uuid, String name) {
        super(name);
        this.id = uuid;
    }

    public static Project fromData(UUID uuid, ProjectData data) {
        return new Project(uuid, data.getName());
    }

    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toCSV() {
        return this.getId() + "," + this.getName();
    }

    @Override
    public void fromCSV(String csv) {

    }
}
