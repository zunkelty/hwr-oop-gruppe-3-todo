package hwr.oop.todo.persistence;

public class FailedDatabaseConnectionException extends RuntimeException{
    public FailedDatabaseConnectionException() {
        super("Failed to connect to database");
    }
}
