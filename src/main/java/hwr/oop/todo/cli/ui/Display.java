package hwr.oop.todo.cli.ui;

import hwr.oop.todo.cli.ui.menu.MenuAction;
import hwr.oop.todo.cli.ui.menu.responses.Table;

import java.io.IOException;
import java.util.List;

public interface Display {
    void displayMessage(String message) throws IOException;
    void displayTable(Table table) throws IOException;

    void displayMenuActions(List<MenuAction> menuActions) throws IOException;
}
