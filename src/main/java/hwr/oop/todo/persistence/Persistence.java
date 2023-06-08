package hwr.oop.todo.persistence;

import hwr.oop.todo.application.ports.*;

public interface Persistence extends InsertProjectPort, InsertTagPort, InsertTaskPort, UpdateProjectPort, UpdateTaskPort, LoadToDoListPort{
}
