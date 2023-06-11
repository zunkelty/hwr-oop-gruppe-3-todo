package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;

import java.util.Optional;

public class ErrorResponse implements MenuResponse {
    String message;
    public static ErrorResponse withMessage(String message){
        return new ErrorResponse(message);
    }

    public static ErrorResponse withUnknownCause(){
        return new ErrorResponse();
    }

    private ErrorResponse(){}

    private ErrorResponse(String message){
        this.message = message;
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
        return Optional.ofNullable(message);
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
