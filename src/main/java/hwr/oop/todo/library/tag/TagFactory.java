package hwr.oop.todo.library.tag;

import java.util.UUID;

public class TagFactory{

    private TagFactory(){
        throw new IllegalStateException("Utility class");
    }

    public static Tag createTag(String title, String description){
        UUID id = UUID.randomUUID();
        return new Tag(id, title, description);
    }

    public static Tag createTag(String name){
        UUID id = UUID.randomUUID();
        return new Tag(id, name);
    }

    public static Tag createTag(UUID id, String title, String description){
        return new Tag(id, title, description);
    }
}
