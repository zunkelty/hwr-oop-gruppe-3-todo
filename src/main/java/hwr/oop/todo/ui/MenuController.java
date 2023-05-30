package hwr.oop.todo.ui;

import hwr.oop.todo.io.CLI;
import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.menu.Menu;
import hwr.oop.todo.ui.menu.responses.MenuResponse;

import java.util.HashMap;
import java.util.Optional;

public class MenuController {

    private Menu currentMenu = Menus.HOME;
    private final ToDoList toDoList;
    private final CLI cli;

    public MenuController(ToDoList toDoList, CLI cli){
        this.toDoList = toDoList;
        this.cli = cli;
    }

    public void execute(){
        cli.displayMenuActions(currentMenu.getActions());

        char key = cli.promptNavigationKeyEntry();

        MenuResponse menuResponse = currentMenu.handle(key, toDoList, cli);

        if(!menuResponse.isSuccess()){
            cli.displayMessage("Das hat leider nicht geklappt...");
        }

        // Print optional message
        Optional<String> message = menuResponse.message();
        message.ifPresent(cli::displayMessage);

        // Print optional table
        Optional<HashMap<String, String>> table = menuResponse.table();
        table.ifPresent(cli::displayTable);

        // Check if should navigate
        Optional<Menu> nextMenu = menuResponse.navigationTarget();
        nextMenu.ifPresent(menu -> currentMenu = menu);

        execute();
    }

}
