package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.QuitResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class QuitResponseTest {
    @Test
    void hasNoMessage(){
        QuitResponse response = new QuitResponse();

        assertFalse(response.message().isPresent());
    }

    @Test
    void hasNoNavigationTarget(){
        QuitResponse response = new QuitResponse();
        assertFalse(response.navigationTarget().isPresent());
    }

    @Test
    void isSuccessful(){
        QuitResponse response = new QuitResponse();
        assertTrue(response.isSuccess());
    }

    @Test
    void hasNoTable(){
        QuitResponse response = new QuitResponse();
        assertFalse(response.table().isPresent());
    }

    @Test
    void shouldQuitIsTrue() {
        QuitResponse response = new QuitResponse();
        assertTrue(response.shouldQuit());
    }
}
