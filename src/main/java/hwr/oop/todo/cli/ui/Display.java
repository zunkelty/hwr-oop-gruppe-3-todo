package hwr.oop.todo.cli.ui;

import hwr.oop.todo.cli.ui.menu.MenuAction;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Display {
    void displayMessage(String message) throws IOException;
    void displayTable(Map<String, String> table) throws IOException;

    void displayMenuActions(List<MenuAction> menuActions) throws IOException;
}
