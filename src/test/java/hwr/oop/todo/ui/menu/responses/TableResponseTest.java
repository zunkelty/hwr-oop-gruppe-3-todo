package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.TableResponse;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class TableResponseTest {

    @Test
    void CanCreateWithTable(){
        LinkedHashMap<String, String> table = new LinkedHashMap<>();
        table.put("Name", "Moritz");
        table.put("Alter", "18");
        table.put("Wohnort", "Ludwigsfelde");

        TableResponse response = new TableResponse()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        assertTrue(response.table().isPresent());
        assertEquals(table, response.table().get());
    }

    @Test
    void MessageIsNeverSet(){
        TableResponse response = new TableResponse()
                .withRow("key1", "value1")
                .withRow("key2", "value2");

        assertFalse(response.message().isPresent());
    }

    @Test
    void IsSuccessIsAlwaysTrue(){
        TableResponse response = new TableResponse()
                .withRow("key1", "value1")
                .withRow("key2", "value2");

        assertTrue(response.isSuccess());
    }

    @Test
    void NavigationTargetIsNeverSet(){
        TableResponse response = new TableResponse()
                .withRow("key1", "value1")
                .withRow("key2", "value2");

        assertFalse(response.navigationTarget().isPresent());
    }

}
