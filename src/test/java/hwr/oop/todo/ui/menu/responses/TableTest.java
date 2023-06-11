package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.Table;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {
    private Table table;

    @BeforeEach
    void setUp() {
        table = new Table();
    }

    @Test
    void canHaveRows() {
        table.withRow("Name1", "Value1");
        table.withRow("Name2", "Value2");

        List<Table.Row> rows = table.getRows();
        assertEquals(2, rows.size());

        Table.Row row1 = rows.get(0);
        assertEquals("Name1: Value1", row1.toString());

        Table.Row row2 = rows.get(1);
        assertEquals("Name2: Value2", row2.toString());
    }

    @Test
    void canHaveDividerRow() {
        table.withRow("Name1", "Value1");
        table.withDividerRow();
        table.withRow("Name2", "Value2");

        List<Table.Row> rows = table.getRows();
        assertEquals(3, rows.size());

        Table.Row dividerRow = rows.get(1);
        assertEquals("---------------------", dividerRow.toString());
    }

    @Test
    void equalTablesHaveEqualStringRepresentation() {
        table.withRow("Name1", "Value1");
        table.withDividerRow();
        table.withRow("Name2", "Value2");

        String expected = "Name1: Value1\n---------------------\nName2: Value2\n";
        assertEquals(expected, table.toString());
    }

    @Test
    void tablesAreEquals() {
        table.withRow("Name1", "Value1");
        table.withRow("Name2", "Value2");

        Table otherTable = new Table();
        otherTable.withRow("Name1", "Value1");
        otherTable.withRow("Name2", "Value2");

        assertEquals(table, otherTable);
    }

    @Test
    void twoEqualTablesHaveSameHashCode() {
        table.withRow("Name1", "Value1");
        table.withRow("Name2", "Value2");

        Table otherTable = new Table();
        otherTable.withRow("Name1", "Value1");
        otherTable.withRow("Name2", "Value2");

        assertEquals(table.hashCode(), otherTable.hashCode());
    }
}
