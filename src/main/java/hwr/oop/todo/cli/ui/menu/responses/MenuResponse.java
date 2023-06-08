package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Optional;

public interface MenuResponse {
    boolean isSuccess();
    boolean shouldQuit();

    Optional<String> message();

    Optional<Menu> navigationTarget();

    Optional<LinkedHashMap<String, String>> table();
}
