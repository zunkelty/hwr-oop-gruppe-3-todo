package hwr.oop.todo.persistence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FailedDatabaseConnectionExceptionTest {

    @Test
    void forwardsExceptionInMessage() {
        FailedDatabaseConnectionException exception = new FailedDatabaseConnectionException(new Exception("Test"));

        assertTrue(exception.getMessage().contains("Test"));
    }
}
