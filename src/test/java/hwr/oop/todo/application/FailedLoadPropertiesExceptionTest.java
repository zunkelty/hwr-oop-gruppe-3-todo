package hwr.oop.todo.application;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FailedLoadPropertiesExceptionTest{

    @Test
    void forwardsException() {
        FailedLoadPropertiesException exception = new FailedLoadPropertiesException(new Exception("Test"));

        assertTrue(exception.getMessage().contains("Test"));
    }
}
