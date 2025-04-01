import modes.NormalMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

import storage.Storage;
import storage.Milestones;
import ui.Ui;
import storage.AutoAdjust;
import storage.State;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import storage.Milestones;

class NormalModeTest {
    private NormalMode normalMode;
    private Storage storage;
    private State state;
    private Ui ui;
    private Scanner scanner;
    private TypingTargetList typingTargetList;
    private AutoAdjust autoAdjust;
    private TypingAccuracy typingAccuracy;
    private Milestones milestones;


    @BeforeEach
    void setUp() {
        storage = new Storage("TestStorage");
        state = new State(storage);
        ui = new Ui(state);
        milestones = new Milestones("data/milestones.txt");
        typingTargetList = new TypingTargetList();
        autoAdjust = new AutoAdjust(milestones, ui, state);
        typingAccuracy = new TypingAccuracy(new ArrayList<>());
        scanner = new Scanner(System.in);
        normalMode = new NormalMode(ui, scanner, typingTargetList, state, autoAdjust, typingAccuracy);
    }

    @Test
    void testStartNormalMode_CalculatesTypingSpeedCorrectly() {
        String input = "Hello world\nThis is a test\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        normalMode = new NormalMode(ui, scanner, typingTargetList, state, autoAdjust, typingAccuracy);

        List<String> testText = Arrays.asList("Hello world", "This is a test");
        typingAccuracy.setTestText((new ArrayList<>(testText)));

        normalMode.startNormalMode(testText);

        assertTrue(typingAccuracy.getTypingAccuracy() >= 0);
    }

    @Test
    void testStartNormalMode_HandlesHighScoreUpdate() throws IOException {
        String input = "Hello world\nThis is a test\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        scanner = new Scanner(System.in);
        normalMode = new NormalMode(ui, scanner, typingTargetList, state, autoAdjust, typingAccuracy);

        List<String> testText = Arrays.asList("Hello world", "This is a test");
        typingAccuracy.setTestText((new ArrayList<>(testText)));

        normalMode.startNormalMode(testText);

        assertDoesNotThrow(() -> state.updateHighScore(typingAccuracy.getTypingAccuracy(), 10));
    }
}
