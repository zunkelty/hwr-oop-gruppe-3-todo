package hwr.oop.todo.cli;

import hwr.oop.todo.cli.ui.Display;
import hwr.oop.todo.cli.ui.NavigationInput;
import hwr.oop.todo.cli.ui.menu.FailedWriteException;
import hwr.oop.todo.cli.ui.menu.MenuAction;
import hwr.oop.todo.cli.ui.ParameterProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class IO implements ParameterProvider, NavigationInput, Display {
    private static final int TABLE_VERTICAL_PADDING = 1;

    Scanner in;
    OutputStream out;

    public IO(InputStream in, OutputStream out){
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

    private String readLine() {
        if (in.hasNextLine()) {
            String line = in.nextLine();
            return line.isEmpty() ? "\n" : line;
        } else {
            throw new NoSuchElementException();
        }
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

        int longest = lines.stream().mapToInt(String::length).max().orElse(0);

        StringBuilder seperatorLine = new StringBuilder();
        seperatorLine.append("-".repeat(longest + TABLE_VERTICAL_PADDING * 2));

        String verticalPadding = " ".repeat(TABLE_VERTICAL_PADDING);

        writeLine("|" + seperatorLine + "|");
        for (String line : lines) {
            writeLine("|"+ verticalPadding + line + " ".repeat(Math.max(0, longest - line.length())) + verticalPadding + "|");
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
        writeLine("Press a key to continue");


        String input = readLine();

        if (input.trim().length() == 0) {
            return promptNavigationKeyEntry();
        }

        return input.charAt(0);
    }



}