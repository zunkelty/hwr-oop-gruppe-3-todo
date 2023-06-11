package hwr.oop.todo.application;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AppConfigTests {

    @Test
    void canLoadValidProperty() {
        assertNotNull(AppConfig.getProperty("db.url"));
    }

    @Test
    void canLoadInvalidProperty() {
        assertNull(AppConfig.getProperty("db.url.invalid"));
    }

}
