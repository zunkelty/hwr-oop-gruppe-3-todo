package hwr.oop.todo;

import hwr.oop.todo.library.tag.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TagTest {

    @Test
    void canCreateTagWithNameOnly(){
        Tag tag = new Tag("Name");
        assertEquals("Name", tag.getName());
    }

    @Test
    void canCreateTagWithDescription(){
        Tag tag = new Tag("Name", "Description");
        assertEquals("Name", tag.getName());
        assertEquals("Description", tag.getDescription());
    }

    @Test
    void tagsAreEqualWithEqualTitles(){
        Tag first = new Tag("First");
        Tag second = new Tag("First");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tagsAreEqualWithEqualContents(){
        Tag first = new Tag("Name", "Description");
        Tag second = new Tag("Name", "Description");

        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void tagsAreNotEqualWithDifferentContents(){
        Tag first = new Tag("Name_", "Description");
        Tag second = new Tag("Name", "Description");

        assertNotEquals(first, second);
        assertNotEquals(first.hashCode(), second.hashCode());
    }

}
