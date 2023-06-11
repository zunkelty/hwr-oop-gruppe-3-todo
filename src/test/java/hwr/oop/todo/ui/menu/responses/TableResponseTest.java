package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.Table;
import hwr.oop.todo.cli.ui.menu.responses.TableResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableResponseTest {

    @Test
    void CanCreateWithTable(){
        Table table = new Table()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        TableResponse response = TableResponse.withTable(table);

        assertTrue(response.table().isPresent());
        assertEquals(table, response.table().get());
    }

    @Test
    void messageIsNeverSet(){
        Table table = new Table()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        TableResponse response = TableResponse.withTable(table);

        assertFalse(response.message().isPresent());
    }

    @Test
    void isSuccessIsAlwaysTrue(){
        Table table = new Table()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        TableResponse response = TableResponse.withTable(table);

        assertTrue(response.isSuccess());
    }

    @Test
    void navigationTargetIsNeverSet(){
        Table table = new Table()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        TableResponse response = TableResponse.withTable(table);

        assertFalse(response.navigationTarget().isPresent());
    }

    @Test
    void shouldNotQuit() {
        Table table = new Table()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        TableResponse response = TableResponse.withTable(table);

        assertFalse(response.shouldQuit());
    }

}
