package hwr.oop.todo.cli.ui.menu;

import hwr.oop.todo.application.usecases.UseCases;
import hwr.oop.todo.cli.ui.menu.responses.MenuResponse;
import hwr.oop.todo.cli.ui.ParameterProvider;

public interface MenuActionHandlerFunction {
    MenuResponse run(UseCases useCases, ParameterProvider parameterProvider);
}
