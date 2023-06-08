package hwr.oop.todo.persistence;

import hwr.oop.todo.library.project.Project;
import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.task.Task;
import hwr.oop.todo.library.todolist.ToDoList;

public class DatabaseAdapter implements Persistence {
    @Override
    public void insertProject(Project project) {

    }

    @Override
    public void insertTag(Tag tag) {

    }

    @Override
    public void insertTask(Task task) {

    }

    @Override
    public ToDoList loadToDoList() {
        return new ToDoList();
    }

    @Override
    public void updateProject(Project project) {
    }

    @Override
    public void updateTask(Task task) {

    }
}
