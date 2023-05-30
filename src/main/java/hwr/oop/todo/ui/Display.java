package hwr.oop.todo.ui;

import hwr.oop.todo.ui.menu.MenuAction;

import java.util.List;
import java.util.Map;

public interface Display {
    void displayMessage(String message);
    void displayTable(Map<String, String> table);

    void displayMenuActions(List<MenuAction> menuActions);
}
