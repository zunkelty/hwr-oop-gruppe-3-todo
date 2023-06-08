package hwr.oop.todo.persistence;

public class FailedDatabaseStatementException extends RuntimeException{
    public FailedDatabaseStatementException() {
        super("Failed to execute statement");
    }
}
