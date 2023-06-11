package hwr.oop.todo.cli;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CLITest {
    @Test
    void printsToOutputStream() {
        InputStream in = new ByteArrayInputStream("q".getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        CLI cli = new CLI(in, out, null);
        cli.start();

        String output = out.toString();
        String expected =
                "|---------------------------------|" + System.lineSeparator() +
                        "| a: Tasks anzeigen/bearbeiten    |" + System.lineSeparator() +
                        "| b: Projekte anzeigen/bearbeiten |" + System.lineSeparator() +
                        "| c: Tags anzeigen/bearbeiten     |" + System.lineSeparator() +
                        "| d: Eingangsliste                |" + System.lineSeparator() +
                        "| q: Beenden                      |" + System.lineSeparator() +
                        "|---------------------------------|" + System.lineSeparator() +
                        "Press a key to continue" + System.lineSeparator() +
                        "Auf Wiedersehen!"+System.lineSeparator();

        assertEquals(expected, output);
    }
}
