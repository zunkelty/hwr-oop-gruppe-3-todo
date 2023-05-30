package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.HashMap;
import java.util.Optional;

public class UnknownErrorResponse implements MenuResponse {
    @Override
    public boolean isSuccess() {
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
    public Optional<HashMap<String, String>> table() {
        return Optional.empty();
    }


}
