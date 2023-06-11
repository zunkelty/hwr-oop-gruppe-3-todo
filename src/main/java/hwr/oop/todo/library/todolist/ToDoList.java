package hwr.oop.todo.library.todolist;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.TaskState;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ToDoList {
    private final HashMap<UUID, Task> tasks = new HashMap<>();
    private final HashMap<UUID, Project> projects = new HashMap<>();
    private final HashMap<UUID, Tag> tags = new HashMap<>();
    private final HashMap<UUID, Task> inTray = new HashMap<>();
    public void createTask(Task task){
        UUID id = task.getId();
        if(tasks.containsKey(id)) throw new DuplicateIdException(id);
        tasks.put(id, task);
    }

    public Task getTask(UUID id){
        if(!tasks.containsKey(id)) throw NotFoundException.withItemName("Task");
        return tasks.get(id);
    }

    public void createInTrayTask(Task task){
        UUID id = task.getId();
        if(inTray.containsKey(id)) throw new DuplicateIdException(id);
        inTray.put(id, task);
    }

    public Task getInTrayTask(UUID id){
        if(!inTray.containsKey(id)) throw NotFoundException.withItemName("InTrayTask");
        return inTray.get(id);
    }

    public void deleteInTrayTask(UUID id){
        if(!inTray.containsKey(id)) throw NotFoundException.withItemName("InTrayTask");
        inTray.remove(id);
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

    public Task editTask(Task task){
        UUID id = task.getId();
        if(!tasks.containsKey(id)) throw NotFoundException.withItemName("Task");
        tasks.put(id, task);
        return task;
    }

    public List<Task> getOpenTasks(){
        return tasks.values().stream().filter(task -> task.getState() != TaskState.DONE).collect(Collectors.toList());
    }
}
