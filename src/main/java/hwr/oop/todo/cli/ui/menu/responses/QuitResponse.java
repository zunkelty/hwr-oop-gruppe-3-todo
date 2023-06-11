package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.Optional;

public class QuitResponse implements MenuResponse{


    @Override
    public boolean isSuccess() {
        return true;
    }

    @Override
    public boolean shouldQuit() {
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
    public Optional<Table> table() {
        return Optional.empty();
    }


}
