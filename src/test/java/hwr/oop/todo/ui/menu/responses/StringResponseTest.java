package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.StringResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringResponseTest {

    @Test
    void CanCreateWithMessage(){
        StringResponse response = StringResponse.with("This is a message!");

        assertTrue(response.message().isPresent());
        assertEquals("This is a message!", response.message().get());
    }

    @Test
    void NavigationTargetIsNeverSet(){
        StringResponse response = StringResponse.with("This is message!");

        assertFalse(response.navigationTarget().isPresent());
    }

    @Test
    void IsSuccessIsAlwaysTrue(){
        StringResponse response = StringResponse.with("This is message!");

        assertTrue(response.isSuccess());
    }

    @Test
    void TableIsNeverSet(){
        StringResponse response = StringResponse.with("This is message!");

        assertFalse(response.table().isPresent());
    }

    @Test
    void shouldNotQuit() {
        StringResponse response = StringResponse.with("This is message!");
        assertFalse(response.shouldQuit());
    }

}
