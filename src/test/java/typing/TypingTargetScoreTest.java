package typing;

//@@author rodi-314

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TypingTargetScoreTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void printHit_hit_printsCorrectOutput() {
        TypingTargetScore typingTargetScore = new TypingTargetScore(42, true);
        typingTargetScore.printHit();
        String output = outputStream.toString();
        assertTrue(output.contains(" Congratulations! Typing Score Target has been hit: 42 WPM"));
    }

    @Test
    public void printHit_notHit_printsCorrectOutput() {
        TypingTargetScore typingTargetScore = new TypingTargetScore(84, false);
        typingTargetScore.printHit();
        String output = outputStream.toString();
        assertTrue(output.contains(" Almost there! Hit this target next time: 84 WPM"));
    }

    @Test
    public void getString_hit_returnsCorrectString() {
        TypingTargetScore typingTargetScore = new TypingTargetScore(42, true);
        assertEquals(" Target Score (Effective WPM): 42 | Achieved", typingTargetScore.getString());
    }

    @Test
    public void getString_notHit_returnsCorrectString() {
        TypingTargetScore typingTargetScore = new TypingTargetScore(84, false);
        assertEquals(" Target Score (Effective WPM): 84 | Not Achieved", typingTargetScore.getString());
    }

    @Test
    public void setHit_hit_success() {
        TypingTargetScore typingTargetScore = new TypingTargetScore(69, false);
        typingTargetScore.setHit(true);
        assertTrue(typingTargetScore.getHit());
    }

    @Test
    public void setHit_notHit_success() {
        TypingTargetScore typingTargetScore = new TypingTargetScore(69, true);
        typingTargetScore.setHit(false);
        assertFalse(typingTargetScore.getHit());
    }
}
