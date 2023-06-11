package hwr.oop.todo.application.ports;

import java.util.UUID;

public interface DeleteInTrayTaskPort {
    void deleteInTrayTask(UUID id);
}
