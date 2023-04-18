package hwr.oop.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Task {
    private String title;
    private String description;
    private TaskState state;
    private List<Tag> tags = new ArrayList<>();

    public Task(String title, String description){
        this.title = title;
        this.description = description;
        this.state = TaskState.OPEN;
    }

    public Task(String title) {
        this(title, "");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return title.equals(task.title) && description.equals(task.description) && state == task.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, state);
    }
}
