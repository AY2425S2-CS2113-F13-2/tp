package modes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.State;
import storage.Storage;
import ui.Ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class CustomModeTest {

    private TypingTimer typingTimer;
    private Ui ui;
    private Scanner scanner;
    private CustomMode customMode;

    @BeforeEach
    public void setUp() {
        typingTimer = new TestTypingTimer();
        scanner = new Scanner("");
        Storage storage = new Storage("TestStorage");
        State state = new State(storage);
        ui = new Ui(state);
    }

    @Test
    public void testStartCustomMode() {
        scanner = new Scanner("test\nexit\n");
        customMode = new CustomMode(ui, scanner);

        assertDoesNotThrow(() -> {
            try {
                customMode.startCustomMode();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
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

    @Test
    public void testStartCustomModeWithLongText() {
        String longText = "This is a very long text input to test the custom mode functionality over " +
                "a longer period of time.\nexit\n";
        scanner = new Scanner(new ByteArrayInputStream(longText.getBytes()));
        customMode = new CustomMode(ui, scanner);
        assertDoesNotThrow(() -> customMode.startCustomMode());
    }

    @Test
    public void testStartCustomModeWithMixedInput() {
        String mixedInput = "12345\n!@#$%\nHello World\nexit\n";
        scanner = new Scanner(new ByteArrayInputStream(mixedInput.getBytes()));
        customMode = new CustomMode(ui, scanner);
        assertDoesNotThrow(() -> customMode.startCustomMode());
    }

    private static class TestTypingTimer extends TypingTimer {
        private double typingDuration = 1.0;

        @Override
        public double getDurationMin() throws IllegalStateException {
            return typingDuration;
        }
    }
}
