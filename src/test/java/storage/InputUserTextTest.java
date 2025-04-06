package storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InputUserTextTest {

    private Ui ui;
    private InputUserText inputUserText;

    @BeforeEach
    public void setUp() {
        Storage storage = new Storage("TestStorage");
        State state = new State(storage);
        ui = new Ui(state);
        inputUserText = new InputUserText(ui);
    }

    @Test
    public void testInputTextWithMultipleLines() {
        System.setIn(new ByteArrayInputStream("line one\nline two\nexit\n".getBytes()));
        List<String> result = inputUserText.inputText();
        assertEquals(2, result.size());
        assertEquals("line one", result.get(0));
        assertEquals("line two", result.get(1));
    }

    @Test
    public void testInputTextWithEmptyInput() {
        System.setIn(new ByteArrayInputStream("exit\n".getBytes()));
        List<String> result = inputUserText.inputText();
        assertTrue(result.isEmpty());
    }

    @Test
    public void testInputTextWithSpecialCharacters() {
        System.setIn(new ByteArrayInputStream("!@#$%^&*()\nexit\n".getBytes()));
        List<String> result = inputUserText.inputText();
        assertEquals(1, result.size());
        assertEquals("!@#$%^&*()", result.get(0));
    }

    @Test
    public void testFileCreation() throws IOException {
        System.setIn(new ByteArrayInputStream("test input\nexit\n".getBytes()));
        inputUserText.inputText();
        File file = new File("data/user_text/inputs.txt");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}
