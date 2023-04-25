package hwr.oop.todo;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOControllerTest {

    @Test
    void canPrintPropmpt(){
        //InputStream inputStream = createInputStreamForInput("TEst");
        //OutputStream outputStream = new ByteArrayOutputStream();
        //IOController controller = new IOController(inputStream);
        // TODO: add printing function
        //String output = retrieveResultFrom(outputStream);
        //assertEquals("TEst", output);
    }

    @Test
    void canGetInput(){
        InputStream inputStream = createInputStreamForInput("AAA\n");
        IOController controller = new IOController(inputStream);
        String input = controller.getInput();

        assertEquals("AAA", input);
    }

    private String retrieveResultFrom(OutputStream outputStream) {
        String outputText = outputStream.toString();
        String key = "output:";
        return outputText.substring(outputText.indexOf(key) + key.length()).trim();
    }

    private InputStream createInputStreamForInput(String input) {
        byte[] inputInBytes = input.getBytes();
        return new ByteArrayInputStream(inputInBytes);
    }
}