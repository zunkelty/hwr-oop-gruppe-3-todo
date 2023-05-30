package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.HashMap;
import java.util.Optional;

public class TableResponse implements MenuResponse{
    private final HashMap<String, String> response;

    public TableResponse(){
        response = new HashMap<>();
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
    public Optional<HashMap<String, String>> table() {
        return Optional.of(response);
    }
}
