package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.ui.Menus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NavigationResponseTest {

    @Test
    void CanCreateWithMessage(){
        NavigationResponse response = NavigationResponse.to(Menus.HOME);

        assertTrue(response.navigationTarget().isPresent());
        assertEquals(Menus.HOME, response.navigationTarget().get());
    }

    @Test
    void MessageIsNeverSet(){
        NavigationResponse response = NavigationResponse.to(Menus.HOME);

        assertFalse(response.message().isPresent());
    }

    @Test
    void IsSuccessIsAlwaysTrue(){
        NavigationResponse response = NavigationResponse.to(Menus.HOME);
        assertTrue(response.isSuccess());
    }

    @Test
    void TableIsNeverSet(){
        NavigationResponse response = NavigationResponse.to(Menus.HOME);
        assertFalse(response.table().isPresent());
    }

}
