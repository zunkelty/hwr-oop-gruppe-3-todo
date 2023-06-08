package hwr.oop.todo.cli.ui.menu.responses;

import hwr.oop.todo.cli.ui.menu.Menu;
import hwr.oop.todo.cli.ui.menu.MenuActionHandlerFunction;
import hwr.oop.todo.cli.ui.menu.NavigateToMenuFunction;

public class MenuResponseInContext {

    private final char key;
    private final String description;
    private final Menu menu;

    public MenuResponseInContext(char key, String description, Menu menu){
        this.key = key;
        this.description = description;
        this.menu = menu;
    }

    private void registerHandler(MenuActionHandlerFunction handler){
        menu.addAction(key, description, handler);
    }

    public Menu navigateTo(NavigateToMenuFunction nextMenu){
        registerHandler((toDoList, parameters) -> NavigationResponse.to(nextMenu.get()));
        return menu;
    }

    public Menu quit(){
        registerHandler((toDoList, parameters) -> new QuitResponse());
        return menu;
    }

    public Menu execute(MenuActionHandlerFunction handler) {
        registerHandler(handler);
        return menu;
    }

}
