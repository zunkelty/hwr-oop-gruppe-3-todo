package hwr.oop.todo;

import static org.junit.jupiter.api.Assertions.*;

import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.responses.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Optional;

class ResponseTest {

    @Test
    void testNavigationResponse() {
        Menu menu = new Menu();
        NavigationResponse response = NavigationResponse.to(menu);

        assertTrue(response.isSuccess());
        assertEquals(Optional.empty(), response.message());
        assertEquals(Optional.of(menu), response.navigationTarget());
        assertEquals(Optional.empty(), response.table());
    }

    @Test
    void testStringResponse() {
        String responseMessage = "This is a response message.";
        StringResponse response = StringResponse.with(responseMessage);

        assertTrue(response.isSuccess());
        assertEquals(Optional.of(responseMessage), response.message());
        assertEquals(Optional.empty(), response.navigationTarget());
        assertEquals(Optional.empty(), response.table());

    }

    @Test
    void testTableResponse() {
        TableResponse response = new TableResponse()
                .withRow("Name", "Moritz")
                .withRow("Alter", "18")
                .withRow("Wohnort", "Ludwigsfelde");

        LinkedHashMap<String, String> expectedTable = new LinkedHashMap<>();
        expectedTable.put("Name", "Moritz");
        expectedTable.put("Alter", "18");
        expectedTable.put("Wohnort", "Ludwigsfelde");


        assertTrue(response.isSuccess());
        assertEquals(Optional.empty(), response.message());
        assertEquals(Optional.empty(), response.navigationTarget());
        assertEquals(Optional.of(expectedTable), response.table());
    }

    @Test
    void testErrorResponseWithMessage() {
        String errorMessage = "An error occurred.";
        ErrorResponse response = ErrorResponse.withMessage(errorMessage);

        assertFalse(response.isSuccess());
        assertEquals(Optional.of(errorMessage), response.message());
        assertEquals(Optional.empty(), response.navigationTarget());
        assertEquals(Optional.empty(), response.table());
    }

    @Test
    void testErrorResponseWithUnknownCause() {
        ErrorResponse response = ErrorResponse.withUnknownCause();

        assertFalse(response.isSuccess());
        assertEquals(Optional.empty(), response.message());
        assertEquals(Optional.empty(), response.navigationTarget());
        assertEquals(Optional.empty(), response.table());
    }

    @Test
    void testInvalidKeyResponse() {
        char invalidKey = 'X';
        InvalidKeyResponse response = InvalidKeyResponse.withKey(invalidKey);

        assertFalse(response.isSuccess());
        assertEquals(Optional.of("The key '" + invalidKey + "' is not valid."), response.message());
        assertEquals(Optional.empty(), response.navigationTarget());
        assertEquals(Optional.empty(), response.table());
    }
}
