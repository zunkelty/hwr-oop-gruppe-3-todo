package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Optional;

public class NavigationResponse implements MenuResponse {
    private final Menu menu;

    private NavigationResponse(Menu menu){
        this.menu = menu;
    }

    public static NavigationResponse to(Menu menu){
        return new NavigationResponse(menu);
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
        return Optional.of(menu);
    }

    @Override
    public Optional<LinkedHashMap<String, String>> table() {
        return Optional.empty();
    }
}
