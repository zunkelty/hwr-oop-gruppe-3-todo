package hwr.oop.todo.ui.menu;

import hwr.oop.todo.library.todolist.ToDoList;
import hwr.oop.todo.ui.ParameterProvider;
import hwr.oop.todo.ui.menu.responses.MenuResponse;
import hwr.oop.todo.ui.menu.responses.MenuResponseInContext;
import hwr.oop.todo.ui.menu.responses.InvalidKeyResponse;
import hwr.oop.todo.ui.menu.responses.ErrorResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Menu {
    private final Map<Character, MenuAction> actions = new HashMap<>();

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

    public MenuResponse handle(char key, ToDoList toDoList, ParameterProvider parameters){
        if(!actions.containsKey(key)){
            return InvalidKeyResponse.withKey(key);
        }

        try {
            return actions.get(key).run(toDoList, parameters);
        }catch(RuntimeException exception){
            if(exception.getClass().getPackageName().startsWith("hwr.oop.todo")){
                return ErrorResponse.withMessage(exception.getMessage());
            }else{
                return ErrorResponse.withUnknownCause();
            }
        }
    }

}
