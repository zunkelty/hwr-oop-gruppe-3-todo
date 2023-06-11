package hwr.oop.todo.cli.ui.menu.responses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Table {

    private final List<Row> rows;

    public Table(){
        this.rows = new ArrayList<>();
    }

    public Table withRow(String name, String value){
        rows.add(new Row(name, value));
        return this;
    }

    public Table withDividerRow(){
        rows.add(new Row("", ""));
        return this;
    }

    public List<Row> getRows(){
        return rows;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Row row : rows){
            sb.append(row.toString()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return Objects.equals(rows, table.rows);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rows);
    }

    public static class Row {
        private final String name;
        private final String value;

        public Row(String name, String value){
            this.name = name;
            this.value = value;
        }

        @Override
        public String toString(){
            if(name.isEmpty() && value.isEmpty()){
                return "---------------------";
            }

            return String.format("%s: %s", name, value);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Row row = (Row) o;
            return Objects.equals(name, row.name) && Objects.equals(value, row.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, value);
        }
    }

}
