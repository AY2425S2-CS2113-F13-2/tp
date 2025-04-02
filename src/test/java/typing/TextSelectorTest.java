package typing;

import org.junit.jupiter.api.BeforeEach;
import enums.DifficultyLevel;
import enums.TextLength;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import storage.Storage;
import storage.Milestones;
import ui.Ui;
import util.TextSelector;
import util.FileReader;
import storage.State;

class TextSelectorTest {
    private TextSelector textSelector;
    private Scanner scanner;
    private Ui ui;
    private Milestones milestones;
    private FileReader fileReader;

    @BeforeEach
    void setUp() {
        scanner = new Scanner(System.in);
        Storage storage = new Storage("TestStorage");
        State state = new State(storage);
        ui = new Ui(state);
        milestones = new Milestones("data/milestones.txt");
        fileReader = new FileReader();
        textSelector = new TextSelector(scanner, ui);
    }

    @Test
    void testSelectDifficulty_default() {
        milestones.setCurrentDifficulty("easy");
        System.setIn(new java.io.ByteArrayInputStream("\n".getBytes()));
        scanner = new Scanner(System.in);
        textSelector = new TextSelector(scanner, ui);
        DifficultyLevel difficulty = textSelector.selectDifficulty();
        assertEquals(DifficultyLevel.EASY, difficulty);
    }

    @Test
    void testSelectDifficulty_overrideValid() {
        System.setIn(new java.io.ByteArrayInputStream("override\nintermediate\n".getBytes()));
        scanner = new Scanner(System.in);
        textSelector = new TextSelector(scanner, ui);
        DifficultyLevel difficulty = textSelector.selectDifficulty();
        assertEquals(DifficultyLevel.INTERMEDIATE, difficulty);
    }

    @Test
    void testSelectDifficulty_overrideInvalidThenValid() {
        System.setIn(new java.io.ByteArrayInputStream("override\ninvalid\ndifficult\n".getBytes()));
        scanner = new Scanner(System.in);
        textSelector = new TextSelector(scanner, ui);
        DifficultyLevel difficulty = textSelector.selectDifficulty();
        assertEquals(DifficultyLevel.DIFFICULT, difficulty);
    }

    @Test
    void testSelectLength_validInput() {
        System.setIn(new java.io.ByteArrayInputStream("short\n".getBytes()));
        scanner = new Scanner(System.in);
        textSelector = new TextSelector(scanner, ui);
        TextLength length = textSelector.selectLength();
        assertEquals(TextLength.SHORT, length);
    }

    @Test
    void testSelectLength_invalidThenValidInput() {
        System.setIn(new java.io.ByteArrayInputStream("invalid\nmedium\n".getBytes()));
        scanner = new Scanner(System.in);
        textSelector = new TextSelector(scanner, ui);
        TextLength length = textSelector.selectLength();
        assertEquals(TextLength.MEDIUM, length);
    }

    @Test
    void testSelectText_successfulRead() {
        System.setIn(new java.io.ByteArrayInputStream("\nshort\n".getBytes()));
        scanner = new Scanner(System.in);
        textSelector = new TextSelector(scanner, ui);

        List<String> text = textSelector.selectText();
        assertNotNull(text);
        assertFalse(text.isEmpty());
    }

}
