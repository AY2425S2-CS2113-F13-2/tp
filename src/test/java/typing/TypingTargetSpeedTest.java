package typing;

//@@author rodi-314

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TypingTargetSpeedTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void printHit_hit_printsCorrectOutput() {
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(42, true);
        typingTargetSpeed.printHit();
        String output = outputStream.toString();
        assertTrue(output.contains(" Congratulations! Typing Speed Target has been hit: 42 WPM"));
    }

    @Test
    public void printHit_notHit_printsCorrectOutput() {
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(84, false);
        typingTargetSpeed.printHit();
        String output = outputStream.toString();
        assertTrue(output.contains(" Almost there! Hit this target next time: 84 WPM"));
    }

    @Test
    public void getString_hit_returnsCorrectString() {
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(42, true);
        assertEquals(" Target Speed (WPM): 42 | Achieved", typingTargetSpeed.getString());
    }

    @Test
    public void getString_notHit_returnsCorrectString() {
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(84, false);
        assertEquals(" Target Speed (WPM): 84 | Not Achieved", typingTargetSpeed.getString());
    }

    @Test
    public void setHit_hit_success() {
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(69, false);
        typingTargetSpeed.setHit(true);
        assertTrue(typingTargetSpeed.getHit());
    }

    @Test
    public void setHit_notHit_success() {
        TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(69, true);
        typingTargetSpeed.setHit(false);
        assertFalse(typingTargetSpeed.getHit());
    }
}
