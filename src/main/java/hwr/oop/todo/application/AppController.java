package hwr.oop.todo.application;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.io.CLI;
import hwr.oop.todo.ui.MenuController;

public class AppController {
    public static final ToDoList toDoList = new ToDoList();

    @SuppressWarnings("java:S106")
    public static final CLI io = new CLI(System.in, System.out);

    public static final MenuController ui = new MenuController(toDoList, io);

    public static void main(String[] args){
        AppConfig.loadProperties();
        ui.execute();
    }

}
