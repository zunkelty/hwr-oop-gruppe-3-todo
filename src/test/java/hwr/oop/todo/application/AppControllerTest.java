package hwr.oop.todo.application;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


class AppControllerTest {
    private static ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private static final PrintStream originalOut = System.out;
    private static final InputStream originalIn = System.in;

    @BeforeAll
    static void setup() {
        System.setOut(new PrintStream(outContent));
        System.setIn(new ByteArrayInputStream("q".getBytes()));
    }

    @AfterAll
    static void restore() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @BeforeEach
    void setUp() throws IOException {
        outContent.close();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void appControllerPrintsHomeMenu() {
        AppController.main(new String[]{});

        String output = outContent.toString();
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

