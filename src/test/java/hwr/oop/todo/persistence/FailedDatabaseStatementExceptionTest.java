package hwr.oop.todo.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FailedDatabaseStatementExceptionTest {

    @Test
    void forwardsException() {
        FailedDatabaseStatementException exception = new FailedDatabaseStatementException(new Exception("Test"));

        assertTrue(exception.getMessage().contains("Test"));
    }
}
