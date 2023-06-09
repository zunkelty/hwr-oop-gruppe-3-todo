package hwr.oop.todo.persistence;

public class FailedDatabaseStatementException extends RuntimeException{
    public FailedDatabaseStatementException(Exception e) {
        super("Failed to execute statement: "+e);
    }
}
