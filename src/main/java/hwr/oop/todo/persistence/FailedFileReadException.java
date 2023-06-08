package hwr.oop.todo.persistence;

public class FailedFileReadException extends RuntimeException{
    public FailedFileReadException(){
        super("Failed to read from file.");
    }
}
