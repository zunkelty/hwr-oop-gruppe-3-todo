package hwr.oop.todo.library.project;

import java.util.UUID;

public class ProjectFactory {
    public static Project createProject(String name){
        UUID id = UUID.randomUUID();
        return new Project(id, name);
    }

    private ProjectFactory() {
    }
}
