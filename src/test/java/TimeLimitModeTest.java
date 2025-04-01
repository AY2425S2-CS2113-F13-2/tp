import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

class TimeLimitModeTest {
    private TimeLimitMode timeLimitMode;
    private WordCounter wordCounter;
    private Ui ui;
    private Scanner sc;

    @BeforeEach
    void setUp() {
        wordCounter = new WordCounter();
        Storage storage = new Storage("TestStorage");
        State state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        timeLimitMode = new TimeLimitMode(ui, sc);
    }

    @Test
    void testGetTimeLimit() {
        String text = "This is a test sentence";
        assertEquals((long) (wordCounter.countWords(text) / 0.67), timeLimitMode.getTimeLimit(text, DifficultyLevel.EASY));
        assertEquals((long) (wordCounter.countWords(text) / 0.83), timeLimitMode.getTimeLimit(text, DifficultyLevel.INTERMEDIATE));
        assertEquals(wordCounter.countWords(text), timeLimitMode.getTimeLimit(text, DifficultyLevel.DIFFICULT));
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
