package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Optional;

public class InvalidKeyResponse implements MenuResponse {
    private final char invalidKey;

    private InvalidKeyResponse(char invalidKey){
        this.invalidKey = invalidKey;
    }

    public static InvalidKeyResponse withKey(char key){
        return new InvalidKeyResponse(key);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public boolean shouldQuit() {
        return false;
    }

    @Override
    public Optional<String> message() {
        return Optional.of("The key '"+invalidKey+"' is not valid.");
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
