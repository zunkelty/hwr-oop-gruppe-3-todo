package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.ErrorResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {

    @Test
    void CanCreateWithMessage(){
        ErrorResponse response = ErrorResponse.withMessage("This is an error message!");

        assertTrue(response.message().isPresent());
        assertEquals("This is an error message!", response.message().get());
    }

    @Test
    void NavigationTargetIsNeverSet(){
        ErrorResponse response = ErrorResponse.withMessage("This is an error message!");

        assertFalse(response.navigationTarget().isPresent());
    }

    @Test
    void IsSuccessIsNeverTrue(){
        ErrorResponse response = ErrorResponse.withMessage("This is an error message!");

        assertFalse(response.isSuccess());
    }

    @Test
    void TableIsNeverSet(){
        ErrorResponse response = ErrorResponse.withMessage("This is an error message!");

        assertFalse(response.table().isPresent());
    }

    @Test
    void shouldNotQuit() {
        ErrorResponse response = ErrorResponse.withMessage("abc");

        assertFalse(response.shouldQuit());
    }
}
