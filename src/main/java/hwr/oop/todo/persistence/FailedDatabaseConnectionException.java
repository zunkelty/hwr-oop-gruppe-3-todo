package hwr.oop.todo.persistence;

public class FailedDatabaseConnectionException extends RuntimeException{
    public FailedDatabaseConnectionException(Exception e) {
        super("Failed to connect to database "+e);
    }
}
