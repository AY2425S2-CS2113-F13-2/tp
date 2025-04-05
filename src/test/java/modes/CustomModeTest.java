package modes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.State;
import storage.Storage;
import ui.Ui;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CustomModeTest {

    private Ui ui;
    private Scanner scanner;
    private CustomMode customMode;

    @BeforeEach
    public void setUp() {
        Storage storage = new Storage("TestStorage");
        State state = new State(storage);
        ui = new Ui(state);
    }

    @Test
    public void testStartCustomMode() {
        scanner = new Scanner(new ByteArrayInputStream("test input\nexit\n".getBytes()));
        customMode = new CustomMode(ui, scanner);
        assertDoesNotThrow(() -> customMode.startCustomMode());
    }

    @Test
    public void testStartCustomModeWithMultipleLines() {
        scanner = new Scanner(new ByteArrayInputStream("line one\nline two\nexit\n".getBytes()));
        customMode = new CustomMode(ui, scanner);
        assertDoesNotThrow(() -> customMode.startCustomMode());
    }

    @Test
    public void testStartCustomModeWithEmptyInput() {
        scanner = new Scanner(new ByteArrayInputStream("exit\n".getBytes()));
        customMode = new CustomMode(ui, scanner);
        assertDoesNotThrow(() -> customMode.startCustomMode());
    }

    @Test
    public void testStartCustomModeWithSpecialCharacters() {
        scanner = new Scanner(new ByteArrayInputStream("!@#$%^&*()\nexit\n".getBytes()));
        customMode = new CustomMode(ui, scanner);
        assertDoesNotThrow(() -> customMode.startCustomMode());
    }
}
