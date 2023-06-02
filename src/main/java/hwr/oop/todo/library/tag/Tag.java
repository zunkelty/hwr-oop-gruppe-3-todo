package hwr.oop.todo.library.tag;

import java.util.Objects;
import java.util.UUID;

public class Tag {
    private final UUID id;
    private final String name;
    private final String description;

    Tag(UUID id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
    }

    Tag(UUID id, String name){
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(id, tag.id) && Objects.equals(name, tag.name) && Objects.equals(description, tag.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
