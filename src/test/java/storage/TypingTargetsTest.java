package storage;

//@@author rodi-314

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import typing.TypingTargetList;
import typing.TypingTargetScore;
import typing.TypingTargetSpeed;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class TypingTargetsTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final String testFilePath = "data/testTypingTargets.txt";
    private TypingTargets typingTargets;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
        // Delete test file if it exists
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        typingTargets = new TypingTargets(testFilePath);
    }

    @AfterEach
    void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void updateAndLoad_success() {
        // Create typing targets file
        TypingTargetList typingTargetList = new TypingTargetList();
        typingTargets.load(typingTargetList);

        // Add target speed
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(100, true);
        typingTargetList.addTarget(typingTargetSpeed);

        // Add target score
        TypingTargetScore typingTargetScore = new TypingTargetScore(69, false);
        typingTargetList.addTarget(typingTargetScore);

        // Update typing targets file
        typingTargets.update(typingTargetList);

        // Reload typing target list
        TypingTargetList reloadedTypingTargetList = new TypingTargetList();
        TypingTargets reloadedTypingTargets = new TypingTargets(testFilePath);
        reloadedTypingTargets.load(reloadedTypingTargetList);

        // Print typing target list
        reloadedTypingTargetList.print();
        String output = outputStream.toString();
        assertTrue(output.contains(" Here is your list of targets!"));
        assertTrue(output.contains(" 1. Target Speed (WPM): 100 | Achieved"));
        assertTrue(output.contains(" 2. Target Score (Effective WPM): 69 | Not Achieved"));
    }
}
