package hwr.oop.todo;
import hwr.oop.todo.application.AppConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AppConfigTests {
    @Test
    void CanInitializeAppConfig() {
        assertNotNull(new AppConfig("test"));
    }

    @Test
    void ThrowsExceptionWhenLoadingProperties(){
        AppConfig config = new AppConfig("//");
        assertThrows(RuntimeException.class, () -> config.loadProperties());
    }
}
