package hwr.oop.todo.library;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.todo.library.tag.Tag;
import hwr.oop.todo.library.tag.TagFactory;

class TagTest {

    @Test
    void canGetName() {
        String tagName = "Example Tag";
        Tag tag = TagFactory.createTag(tagName);

        assertEquals(tagName, tag.getName());
    }

    @Test
    void canGetDescription() {
        String tagName = "Example Tag";
        String tagDescription = "Tag Description";
        Tag tag = TagFactory.createTag(tagName, tagDescription);

        assertEquals(tagDescription, tag.getDescription());
    }

    @Test
    void canGetId() {
        String tagName = "Example Tag";
        Tag tag = TagFactory.createTag(tagName);

        assertNotNull(tag.getId());
    }

    @Test
    void canCompareEquality() {
        String tagName1 = "Tag 1";
        Tag tag1 = TagFactory.createTag(tagName1);

        String tagName2 = "Tag 2";
        Tag tag2 = TagFactory.createTag(tagName2);

        assertNotEquals(tag1, tag2);
    }

    @Test
    void canCompareEqualityWithSameId() {
        String tagName = "Example Tag";
        Tag tag1 = TagFactory.createTag(tagName);
        Tag tag2 = TagFactory.createTag(tagName);

        assertNotEquals(tag1.hashCode(), tag2.hashCode());
    }


}
