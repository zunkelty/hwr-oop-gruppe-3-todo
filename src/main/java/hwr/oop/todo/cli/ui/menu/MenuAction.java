package hwr.oop.todo.cli.ui.menu;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;
import hwr.oop.todo.cli.ui.ParameterProvider;

public class MenuAction {

    private final char key;
    private final String description;
    private final MenuActionHandlerFunction handler;

    public MenuAction(char key, String description, MenuActionHandlerFunction handler){
        this.key = key;
        this.description = description;
        this.handler = handler;
    }

    public char getKey(){
        return key;
    }

    public String getDescription(){
        return description;
    }

    public MenuResponse run(UseCases useCases, ParameterProvider parameterProvider) {
        return handler.run(useCases, parameterProvider);
    }

}
