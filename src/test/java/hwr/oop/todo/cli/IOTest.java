package hwr.oop.todo.cli;

import hwr.oop.todo.cli.ui.menu.FailedWriteException;
import hwr.oop.todo.cli.ui.menu.MenuAction;
import hwr.oop.todo.cli.ui.menu.MenuActionHandlerFunction;
import hwr.oop.todo.cli.ui.menu.responses.StringResponse;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void canAskForRequiredParameter(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("a");
        IO cli = new IO(in, out);

        cli.getRequiredParameter("Name");

        String output = out.toString();
        assertEquals("Name? (*)"+System.lineSeparator(), output);
    }

    @Test
    void willNotAcceptEmptyRequiredParameter(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("\nTestname");
        IO cli = new IO(in, out);

        cli.getRequiredParameter("Name");

        String output = out.toString();
        String expectedOutput = "Name? (*)"+System.lineSeparator()+"Name? (*)"+System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    void canAskForOptionalParameter(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("a");
        IO cli = new IO(in, out);

        cli.getOptionalParameter("Description");

        String output = out.toString();
        assertEquals("Description?"+System.lineSeparator(), output);
    }

    @Test
    void acceptsEmptyOptionalParameter() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("\n");
        IO cli = new IO(in, out);

        cli.getOptionalParameter("Description");

        String output = out.toString();
        String expectedOutput = "Description?"+System.lineSeparator();
        assertEquals(expectedOutput, output);
    }

    @Test
    void canDisplayMessage(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = ByteArrayInputStream.nullInputStream();
        IO cli = new IO(in, out);

        String message = "This is a test message";
        cli.displayMessage(message);

        String output = out.toString();
        assertEquals(message+System.lineSeparator(), output);
    }

    @Test
    void canDisplayMultipleMessages(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = ByteArrayInputStream.nullInputStream();
        IO cli = new IO(in, out);

        String firstMessage = "This is a test message";
        String secondMessage = "This is another test message";

        cli.displayMessage(firstMessage);
        cli.displayMessage(secondMessage);

        String output = out.toString();
        assertEquals(firstMessage+System.lineSeparator()+secondMessage+System.lineSeparator(), output);
    }

    @Test
    void canDisplayTable(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("Test");
        IO cli = new IO(in, out);

        LinkedHashMap<String, String> table = new LinkedHashMap<>();
        table.put("Key1", "Value1");
        table.put("Key2", "Value2");
        cli.displayTable(table);

        String output = out.toString();
        String expectedOutput =
                "|--------------|"+System.lineSeparator()+
                "| Key1: Value1 |"+System.lineSeparator()+
                "| Key2: Value2 |"+System.lineSeparator()+
                "|--------------|"+System.lineSeparator();

        assertEquals(expectedOutput, output);
    }

    @Test
    void canHandleFailedWrites(){
        InputStream in = InputStream.nullInputStream();
        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                throw new IOException();
            }
        };

        IO cli = new IO(in, out);

        assertThrows(FailedWriteException.class, () -> cli.getOptionalParameter("Title"));
    }

    @Test
    void canDisplayMenuActions(){
        MenuActionHandlerFunction handlerFunction = (toDoList, parameterProvider) -> StringResponse.with("Response");
        List<MenuAction> menuActions = List.of(
                new MenuAction('a', "Action 1", handlerFunction),
                new MenuAction('b', "Action 2", handlerFunction),
                new MenuAction('c', "Action 3", handlerFunction)
        );

        InputStream in = InputStream.nullInputStream();
        OutputStream out = new ByteArrayOutputStream();

        IO cli = new IO(in, out);
        cli.displayMenuActions(menuActions);

        String output = out.toString();
        String expectedOutput =
                "|-------------|"+System.lineSeparator()+
                "| a: Action 1 |"+System.lineSeparator()+
                "| b: Action 2 |"+System.lineSeparator()+
                "| c: Action 3 |"+System.lineSeparator()+
                "|-------------|"+System.lineSeparator();

        assertEquals(output, expectedOutput);
    }

    @Test
    void canReadInputKey(){
        InputStream in = createInputStreamForInput("a");
        OutputStream out = new ByteArrayOutputStream();

        IO cli = new IO(in, out);
        char inputKey = cli.promptNavigationKeyEntry();

        String output = out.toString();

        assertEquals('a', inputKey);
        assertEquals(output, "Press a key to continue"+System.lineSeparator());
    }
}
