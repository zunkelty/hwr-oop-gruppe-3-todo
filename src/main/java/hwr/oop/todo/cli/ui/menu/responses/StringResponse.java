package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Optional;

public class StringResponse implements MenuResponse {
    private final String response;

    public StringResponse(String response){
        this.response = response;
    }

    public static StringResponse with(String response){
        return new StringResponse(response);
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
        return Optional.of(response);
    }

    @Override
    public Optional<Menu> navigationTarget() {
        return Optional.empty();
    }

    @Override
    public Optional<LinkedHashMap<String, String>> table() {
        return Optional.empty();
    }


}
