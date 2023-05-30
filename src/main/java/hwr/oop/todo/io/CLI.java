package hwr.oop.todo.io;

import hwr.oop.todo.ui.Display;
import hwr.oop.todo.ui.NavigationInput;
import hwr.oop.todo.ui.menu.FailedWriteException;
import hwr.oop.todo.ui.menu.MenuAction;
import hwr.oop.todo.ui.ParameterProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


public class CLI implements ParameterProvider, NavigationInput, Display {

    Scanner in;
    OutputStream out;

    public CLI(InputStream in, OutputStream out){
        this.in = new Scanner(in);
        this.out = out;
    }

    private void write(String s){
        try {
            out.write(s.getBytes());
        } catch (IOException e) {
            throw new FailedWriteException();
        }
    }

    private void writeLine(String s){
        write(s+System.lineSeparator());
    }

    private String readLine(){
        return in.nextLine();
    }

    @Override
    public String getRequiredParameter(String parameterName) {
        writeLine(parameterName+"? (*)");
        String input = readLine();

        if(input.trim().length() == 0){
            return getRequiredParameter(parameterName);
        }

        return input;
    }

    @Override
    public Optional<String> getOptionalParameter(String parameterName) {
        writeLine(parameterName+"?");
        String input = readLine();

        if(input.trim().length() == 0){
            return Optional.empty();
        }

        return Optional.of(input);
    }

    @Override
    public void displayMessage(String message) {
        writeLine(message);
    }

    @Override
    public void displayTable(Map<String, String> table) {
        List<String> lines = new ArrayList<>();
        table.forEach((key, value) -> lines.add(key+ ": "+value));

        String longest = String.valueOf(lines.stream().max(Comparator.comparingInt(String::length)));

        StringBuilder seperatorLine = new StringBuilder();
        seperatorLine.append("-".repeat(Math.max(0, (int) (longest.length() * 1.0) + 7)));

        writeLine("|" + seperatorLine + "|");
        for (String line : lines) {
            writeLine("| "+ line + " ".repeat(Math.max(0, (int) (longest.length() * 1.0) + 3 - line.length())) + " |");
        }
        writeLine("|" + seperatorLine + "|");
    }

    @Override
    public void displayMenuActions(List<MenuAction> menuActions) {
        Map<String, String> table = new HashMap<>();

        for (MenuAction action : menuActions) {
            table.put(String.valueOf(action.getKey()), action.getDescription());
        }

        displayTable(table);
    }

    @Override
    public char promptNavigationKeyEntry() {
        writeLine("Wähle eine Option");
        String input = readLine();

        if(input.trim().length() == 0){
            return promptNavigationKeyEntry();
        }

        return input.charAt(0);
    }
}