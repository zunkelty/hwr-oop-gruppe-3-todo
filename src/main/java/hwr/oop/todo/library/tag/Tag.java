package hwr.oop.todo.library.tag;

import java.util.Objects;
import java.util.UUID;

public class Tag {
    private final UUID id;
    private String name;
    private String description;

    public Tag(UUID id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Tag(UUID id, String name){
        this(id, name, "");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public UUID getId(){
        return id;
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

}
