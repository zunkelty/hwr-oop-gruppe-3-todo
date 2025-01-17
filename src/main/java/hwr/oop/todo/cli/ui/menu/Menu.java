package hwr.oop.todo.cli.ui.menu;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponseInContext;
import hwr.oop.todo.cli.ui.ParameterProvider;
import hwr.oop.todo.cli.ui.menu.responses.InvalidKeyResponse;
import hwr.oop.todo.cli.ui.menu.responses.ErrorResponse;

import java.util.*;


public class Menu {
    private final Map<Character, MenuAction> actions = new LinkedHashMap<>();

    public MenuResponseInContext on(char key, String description) {
        return new MenuResponseInContext(key, description, this);
    }

    public void addAction(char key, String description, MenuActionHandlerFunction handler){
        MenuAction menuAction = new MenuAction(key, description, handler);
        actions.put(key, menuAction);
    }

    public List<MenuAction> getActions() {
        return new ArrayList<>(actions.values());
    }

    public MenuResponse handle(char key, UseCases useCases, ParameterProvider parameters){
        if(!actions.containsKey(key)){
            return InvalidKeyResponse.withKey(key);
        }

        try {
            return actions.get(key).run(useCases, parameters);
        }catch(RuntimeException exception){
            if(exception.getClass().getPackageName().startsWith("hwr.oop.todo")){
                return ErrorResponse.withMessage(exception.getMessage());
            }else{
                return ErrorResponse.withUnknownCause();
            }
        }
    }

}
