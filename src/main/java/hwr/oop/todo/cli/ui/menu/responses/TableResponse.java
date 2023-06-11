package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.Optional;

public class TableResponse implements MenuResponse{
    private final Table table;

    public TableResponse(Table table){
        this.table = table;
    }

    public static TableResponse withTable(Table table){
        return new TableResponse(table);
    }

    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean shouldQuit() {
        return false;
    }

    @Override
    public Optional<String> message() {
        return Optional.empty();

    }

    @Override
    public Optional<Menu> navigationTarget() {
        return Optional.empty();
    }

    @Override
    public Optional<Table> table() {
        return Optional.of(table);
    }
}
