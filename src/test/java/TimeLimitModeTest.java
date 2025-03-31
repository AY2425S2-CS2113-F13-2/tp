import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class TimeLimitModeTest {
    private TimeLimitMode timeLimitMode;
    private WordCounter wordCounter;

    @BeforeEach
    void setUp() {
        timeLimitMode = new TimeLimitMode();
        wordCounter = new WordCounter();
    }

    @Test
    void testGetTimeLimit() {
        String text = "This is a test sentence";
        assertEquals((long) (wordCounter.countWords(text) / 0.67), timeLimitMode.getTimeLimit(text, "easy"));
        assertEquals((long) (wordCounter.countWords(text) / 0.83), timeLimitMode.getTimeLimit(text, "intermediate"));
        assertEquals(wordCounter.countWords(text), timeLimitMode.getTimeLimit(text, "hard"));
    }

    @Test
    void testGetNumOfCorrectInitiallyZero() {
        assertEquals(0, timeLimitMode.getNumOfCorrect());
    }

    @Test
    void testWaitForInput() throws Exception {
        String simulatedInput = "This is a test\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ClockThread clockThread = new ClockThread();
        clockThread.start();

        String userInput = timeLimitMode.waitForInput(clockThread, reader, 5);

        assertEquals("This is a test", userInput);
        clockThread.interrupt();
    }

    @Test
    void testWaitForEmptyInput() throws Exception {
        String simulatedInput = "";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ClockThread clockThread = new ClockThread();
        clockThread.start();

        String userInput = timeLimitMode.waitForInput(clockThread, reader, 5);

        assertEquals(null, userInput);
        clockThread.interrupt();
    }

}
