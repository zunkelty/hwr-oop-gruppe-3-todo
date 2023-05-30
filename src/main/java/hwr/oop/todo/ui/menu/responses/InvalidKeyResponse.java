package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.HashMap;
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
    public Optional<String> message() {
        return Optional.of("The key '"+invalidKey+"' is not valid.");
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
