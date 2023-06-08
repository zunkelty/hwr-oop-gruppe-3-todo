package hwr.oop.todo.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.responses.InvalidKeyResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvalidKeyResponseTest {

    @Test
    void CanCreateForVariableKey(){
        InvalidKeyResponse response = InvalidKeyResponse.withKey('a');

        assertFalse(response.isSuccess());
        assertTrue(response.message().isPresent());
        assertEquals("The key 'a' is not valid.", response.message().get());
    }

    @Test
    void NavigationTargetIsNeverSet(){
        InvalidKeyResponse response = InvalidKeyResponse.withKey('a');

        assertFalse(response.navigationTarget().isPresent());
    }

    @Test
    void IsSuccessIsNeverTrue(){
        InvalidKeyResponse response = InvalidKeyResponse.withKey('a');

        assertFalse(response.isSuccess());
    }

    @Test
    void TableIsNeverSet(){
        InvalidKeyResponse response = InvalidKeyResponse.withKey('a');

        assertFalse(response.table().isPresent());
    }

}
