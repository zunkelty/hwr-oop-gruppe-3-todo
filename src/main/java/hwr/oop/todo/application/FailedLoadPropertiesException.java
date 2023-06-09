package hwr.oop.todo.application;

public class FailedLoadPropertiesException extends RuntimeException{
    public FailedLoadPropertiesException(Exception e) {
        super("Failed to load properties: "+e);
    }
}
