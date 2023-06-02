package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.menu.Menu;

import java.util.LinkedHashMap;
import java.util.Optional;

public class ErrorResponse implements MenuResponse {
    Optional<String> message;
    public static ErrorResponse withMessage(String message){
        return new ErrorResponse(message);
    }

    public static ErrorResponse withUnknownCause(){
        return new ErrorResponse();
    }

    private ErrorResponse(){
        this.message = Optional.empty();
    }

    private ErrorResponse(String message){
        this.message = Optional.of(message);
    }

    @Override
    public boolean isSuccess() {
        return false;
    }

    @Override
    public Optional<String> message() {
        return message;
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
