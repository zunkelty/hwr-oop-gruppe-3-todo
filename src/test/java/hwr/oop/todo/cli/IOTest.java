package hwr.oop.todo.cli;

import hwr.oop.todo.cli.ui.menu.FailedWriteException;
import hwr.oop.todo.cli.ui.menu.MenuAction;
import hwr.oop.todo.cli.ui.menu.MenuActionHandlerFunction;
import hwr.oop.todo.cli.ui.menu.responses.StringResponse;
import hwr.oop.todo.cli.ui.menu.responses.Table;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IOTest {

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void canAskForRequiredParameter(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("Parameter Name");
        IO cli = new IO(in, out);

        String parameter = cli.getRequiredParameter("Name");

        assertEquals("Parameter Name", parameter);

        String output = out.toString();
        assertEquals("Name? (*)"+System.lineSeparator(), output);
    }

    @Test
    void willNotAcceptEmptyRequiredParameter(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("\nTestname");

        IO cli = new IO(in, out);
        IO cliSpy = spy(cli);

        cliSpy.getRequiredParameter("Name");

        String output = out.toString();
        String expectedOutput = "Name? (*)"+System.lineSeparator()+"Name? (*)"+System.lineSeparator();
        assertEquals(expectedOutput, output);

        verify(cliSpy, times(2)).getRequiredParameter("Name");
    }

    @Test
    void canAskForOptionalParameter(){
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("Parameter");
        IO cli = new IO(in, out);

        Optional<String> parameter = cli.getOptionalParameter("Description");
        assertFalse(parameter::isEmpty);
        assertTrue(parameter.isPresent());
        assertEquals("Parameter", parameter.get());

        String output = out.toString();
        assertEquals("Description?"+System.lineSeparator(), output);
    }

    @Test
    void acceptsEmptyOptionalParameter() {
        OutputStream out = new ByteArrayOutputStream();
        InputStream in = createInputStreamForInput("\n");
        IO cli = new IO(in, out);

        Optional<String> parameter = cli.getOptionalParameter("Description");
        assertTrue(parameter::isEmpty);

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

        Table table = new Table()
                .withRow("Key1", "Value1")
                .withRow("Key2", "Value2");

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

    @Test
    void doesNotAcceptEmptyInputKey(){
        InputStream in = createInputStreamForInput("\na");
        OutputStream out = new ByteArrayOutputStream();

        IO cli = new IO(in, out);
        char inputKey = cli.promptNavigationKeyEntry();

        String output = out.toString();

        assertEquals('a', inputKey);
        assertEquals(output, "Press a key to continue"+System.lineSeparator()+"Press a key to continue"+System.lineSeparator());
    }

    @Test
    void canHandleEmptyInput(){
        InputStream in = ByteArrayInputStream.nullInputStream();
        OutputStream out = new ByteArrayOutputStream();

        IO cli = new IO(in, out);

        assertThrows(NoSuchElementException.class, cli::promptNavigationKeyEntry);
    }
}
