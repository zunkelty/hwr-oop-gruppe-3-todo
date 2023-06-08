package hwr.oop.todo.persistence;

public class FailedFileWriteException extends RuntimeException{
    public FailedFileWriteException(){
        super("Failed to write to file.");
    }
}
