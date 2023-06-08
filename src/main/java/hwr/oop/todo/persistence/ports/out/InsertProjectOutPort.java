package hwr.oop.todo.persistence.ports.out;

import hwr.oop.todo.library.project.Project;

public interface InsertProjectOutPort {
    void insertProject(Project project);
}
