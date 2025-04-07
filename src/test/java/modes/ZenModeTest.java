package modes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.State;
import storage.Storage;
import typing.TypingTimer;
import ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ZenModeTest {

    private TypingTimer typingTimer;
    private Ui ui;
    private Scanner scanner;
    private ZenMode zenMode;

    @BeforeEach
    void setUp() {
        typingTimer = new TestTypingTimer();
        scanner = new Scanner("");
        Storage storage = new Storage("TestStorage");
        State state = new State(storage);
        ui = new Ui(state);

    }

    @Test
    void startZenMode_oneWord_twoWords() {
        scanner = new Scanner("start\nTest\nstop_practice\n");
        zenMode = new ZenMode(typingTimer, scanner, ui);

        zenMode.startZenMode();

        assertEquals(2, zenMode.getWordCount());
        assertEquals(17, zenMode.getCharacterCount());
        assertEquals(2, zenMode.getTypingSpeedInWPM());
        assertEquals(17, zenMode.getTypingSpeedInCPM());
    }

    @Test
    void startZenMode_stopImmediately_oneWord() {
        scanner = new Scanner("start\nstop_practice");
        zenMode = new ZenMode(typingTimer, scanner, ui);

        zenMode.startZenMode();

        assertEquals(1, zenMode.getWordCount());
        assertEquals(13, zenMode.getCharacterCount());
        assertEquals(1, zenMode.getTypingSpeedInWPM());
        assertEquals(13, zenMode.getTypingSpeedInCPM());
    }

    @Test
    void startZenMode_wrongStartCommand_printStartPrompt() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        scanner = new Scanner("not_start\nstart\nstop_practice");
        zenMode = new ZenMode(typingTimer, scanner, ui);

        zenMode.startZenMode();

        System.setOut(originalOut);
        String output = outputStream.toString();
        assertTrue(output.contains("Please type 'start' on a new line to start Zen mode"));
    }


    private static class TestTypingTimer extends TypingTimer {
        private double typingDuration = 1.0;

        @Override
        public double getDurationMin() throws IllegalStateException {
            return typingDuration;
        }
    }

}

