package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Optional;

public class TableResponse implements MenuResponse{
    private final LinkedHashMap<String, String> response;

    public TableResponse(){
        response = new LinkedHashMap<>();
    }

    public TableResponse withRow(String name, String value){
        response.put(name, value);
        return this;
    }

    @Override
    public boolean isSuccess() {
        return true;
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
    public Optional<LinkedHashMap<String, String>> table() {
        return Optional.of(response);
    }
}
