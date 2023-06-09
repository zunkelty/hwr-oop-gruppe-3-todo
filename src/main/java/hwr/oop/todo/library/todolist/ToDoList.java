package hwr.oop.todo.library.todolist;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.tag.Tag;

import java.util.HashMap;
import java.util.UUID;

public class ToDoList {
    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Project> projects = new HashMap<>();
    private final HashMap<UUID, Tag> tags = new HashMap<>();

    public void addTask(Task task){
        UUID id = task.getId();
        if(tasks.containsKey(id)) throw new DuplicateIdException(id);
        tasks.put(id, task);
    }

    public Task getTask(UUID id){
        if(!tasks.containsKey(id)) throw NotFoundException.withItemName("Task");
        return tasks.get(id);
    }

    public void createProject(Project project){
        UUID id = project.getId();
        if(projects.containsKey(id)) throw new DuplicateIdException(id);
        projects.put(id, project);
    }

    public Project getProject(UUID id){
        if(!projects.containsKey(id)) throw NotFoundException.withItemName("Project");
        return projects.get(id);
    }

    public Tag getTag(UUID id) {
        if(!tags.containsKey(id)) throw NotFoundException.withItemName("Tag");
        return tags.get(id);
    }

    public void createTag(Tag tag) {
        UUID id = tag.getId();
        if(tags.containsKey(id)) throw new DuplicateIdException(id);
        tags.put(id, tag);
    }

}
