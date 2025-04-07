package typing;

//@@author rodi-314

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TypingTargetListTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testAllMethods_success() {
        // Initialise typing target list
        TypingTargetList typingTargetList = new TypingTargetList();
        assertEquals(0, typingTargetList.getTargetCount());

        // Add target speed
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(100, true);
        typingTargetList.addTarget(typingTargetSpeed);
        assertEquals(1, typingTargetList.getTargetCount());

        // Add target score
        TypingTargetScore typingTargetScore = new TypingTargetScore(69, false);
        typingTargetList.addTarget(typingTargetScore);
        assertEquals(2, typingTargetList.getTargetCount());

        // Add 3rd target
        typingTargetList.addTarget(new TypingTargetScore(42, false));
        assertEquals(3, typingTargetList.getTargetCount());

        // Remove target
        typingTargetList.removeTarget(3);
        assertEquals(2, typingTargetList.getTargetCount());

        // Print typing target list
        typingTargetList.print();
        String output = outputStream.toString();
        assertTrue(output.contains(" Here is your list of targets!"));
        assertTrue(output.contains(" 1. Target Speed (WPM): 100 | Achieved"));
        assertTrue(output.contains(" 2. Target Score (Effective WPM): 69 | Not Achieved"));

        // Get target
        assertEquals(typingTargetSpeed, typingTargetList.getTarget(1));
        assertEquals(typingTargetScore, typingTargetList.getTarget(2));
    }
}
