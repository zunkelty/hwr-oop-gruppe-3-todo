package hwr.oop.todo.ui.menu;

import hwr.oop.todo.cli.ui.menu.Menu;
import hwr.oop.todo.library.todolist.DuplicateIdException;
import hwr.oop.todo.cli.ui.menu.responses.ErrorResponse;
import hwr.oop.todo.cli.ui.menu.responses.InvalidKeyResponse;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void CanCatchRuntimeExceptions() {
        Menu menu = new Menu().on('a', "test").execute((toDoList, parameterProvider) -> {
            throw new RuntimeException("This is an error message!");
        });

        MenuResponse response = menu.handle('a', null, null);

        assertFalse(response.isSuccess());
        assertInstanceOf(ErrorResponse.class, response);
        assertFalse(response.message().isPresent());
    }

    @Test
    void CanCatchCustomExceptions() {
        UUID id = UUID.randomUUID();
        DuplicateIdException exception = new DuplicateIdException(id);
        Menu menu = new Menu().on('a', "test").execute((toDoList, parameterProvider) -> {
            throw exception;
        });

        MenuResponse response = menu.handle('a', null, null);

        assertFalse(response.isSuccess());
        assertInstanceOf(ErrorResponse.class, response);
        assertTrue(response.message().isPresent());
        assertEquals(exception.getMessage(), response.message().get());
    }

    @Test
    void CanHandleInvalidKey(){
        Menu menu = new Menu().on('a', "test").execute((toDoList, parameterProvider) -> null);

        MenuResponse response = menu.handle('b', null, null);

        assertFalse(response.isSuccess());
        assertInstanceOf(InvalidKeyResponse.class, response);
        assertTrue(response.message().isPresent());
    }
}
