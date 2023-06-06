package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class AppControllerTest {


    @Test
    void testToDoListNotNull() {
        assertNotNull(AppController.toDoList);
    }

    @Test
    void testIOControllerNotNull() {
        assertNotNull(AppController.io);
    }

    @Test
    void testMenuControllerNotNull() {
        assertNotNull(AppController.ui);
    }

}

