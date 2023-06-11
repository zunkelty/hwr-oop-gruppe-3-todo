package hwr.oop.todo.persistence;

import hwr.oop.todo.application.ports.*;

public interface Persistence extends CreateProjectPort, CreateTagPort, CreateTaskPort, UpdateProjectPort, UpdateTaskPort, ReadToDoListPort, CreateInTrayTaskPort, DeleteInTrayTaskPort {
}
