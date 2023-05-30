package hwr.oop.todo;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.io.CLI;
import hwr.oop.todo.ui.MenuController;

public class AppController {
    private static final ToDoList toDoList = new ToDoList();

    @SuppressWarnings("java:S106")
    private static final CLI io = new CLI(System.in, System.out);

    private static final MenuController ui = new MenuController(toDoList, io);

    public static void main(String[] args){
        ui.execute();
    }

}
