package typing;

//@@author rodi-314

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkedTextTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testAllMethods_success() {
        MarkedText markedText = new MarkedText();
        markedText.update("This is the test text line.", "This is a user's test.");
        markedText.print();
        String output = outputStream.toString();
        String ansiRed = "\u001B[31m";
        String ansiGreen = "\u001B[32m";
        assertTrue(output.contains(ansiGreen + "T" + ansiGreen + "h" + ansiGreen + "i" + ansiGreen + "s" + " " +
                ansiGreen + "i" + ansiGreen + "s" + " " +
                ansiRed + "t" + ansiRed + "h" + ansiRed + "e" + " " +
                ansiRed + "t" + ansiRed + "e" + ansiRed + "s" + ansiRed + "t" + ansiRed + "'" + ansiRed + "s" + " " +
                ansiGreen + "t" + ansiGreen + "e" + ansiRed + "x" + ansiGreen + "t" + ansiRed + "." + " " +
                ansiRed + "line."));
    }
}
