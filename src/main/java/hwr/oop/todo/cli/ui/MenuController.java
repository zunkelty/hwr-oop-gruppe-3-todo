package hwr.oop.todo.cli.ui;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.IO;
import hwr.oop.todo.cli.ui.menu.Menu;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;

import java.util.LinkedHashMap;
import java.util.Optional;

public class MenuController {

    private Menu currentMenu = Menus.HOME;
    private final UseCases useCases;
    private final IO cli;

    public MenuController(UseCases useCases, IO cli){
        this.useCases = useCases;
        this.cli = cli;
    }

    public void execute(){
        cli.displayMenuActions(currentMenu.getActions());

        char key = cli.promptNavigationKeyEntry();

        MenuResponse menuResponse = currentMenu.handle(key, useCases, cli);

        if(!menuResponse.isSuccess()){
            cli.displayMessage("Das hat leider nicht geklappt...");
        }

        // Print optional message
        Optional<String> message = menuResponse.message();
        message.ifPresent(cli::displayMessage);

        // Print optional table
        Optional<LinkedHashMap<String, String>> table = menuResponse.table();
        table.ifPresent(cli::displayTable);

        // Check if it should navigate
        Optional<Menu> nextMenu = menuResponse.navigationTarget();
        nextMenu.ifPresent(menu -> currentMenu = menu);

        if(menuResponse.shouldQuit()){
            cli.displayMessage("Auf Wiedersehen!");
        }else{
            execute();
        }
    }

}
