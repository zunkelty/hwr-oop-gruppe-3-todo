package hwr.oop.todo.cli;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CLITest {
    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }

    @Test
    void printsToOutputStream() {
        InputStream in = createInputStreamForInput("q");
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        CLI cli = new CLI(in, out, null);
        cli.start();

        String output = out.toString();
        String expected =
                "|---------------------------------|" + System.lineSeparator() +
                        "| a: Tasks anzeigen/bearbeiten    |" + System.lineSeparator() +
                        "| q: Beenden                      |" + System.lineSeparator() +
                        "| b: Projekte anzeigen/bearbeiten |" + System.lineSeparator() +
                        "| c: Tags anzeigen/bearbeiten     |" + System.lineSeparator() +
                        "|---------------------------------|" + System.lineSeparator() +
                        "Press a key to continue" + System.lineSeparator() +
                        "Auf Wiedersehen!"+System.lineSeparator();

        assertEquals(expected, output);
    }
}
