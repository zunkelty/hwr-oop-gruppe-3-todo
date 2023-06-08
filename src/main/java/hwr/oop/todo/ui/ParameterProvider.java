package hwr.oop.todo.ui;

import java.util.Optional;

public interface ParameterProvider {
    String getRequiredParameter(String name);
    Optional<String> getOptionalParameter(String name);
}
