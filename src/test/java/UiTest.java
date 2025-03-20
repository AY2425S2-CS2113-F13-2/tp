import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final String testFilePath = "data/testBoboType.txt";

    // Create a mock class that implements the State interface
    public static class MockState extends State {
        private String value;

        public MockState(Storage storage) {
            super(storage);
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public Double getHighScore() {
            return 0.0; // Provide a default implementation
        }
    }

    @BeforeEach
    void setUp() {
        Storage storage = new Storage(testFilePath);
        State state = new MockState(storage);
        ui = new Ui(state); // Pass MockState as State
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void showWelcome_printsCorrectOutput() {
        ui.showWelcome();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Welcome to BoboType!"));
        assertTrue(output.contains("Type 'start' to start the game."));
    }

    @Test
    void showExit_printsCorrectOutput() {
        ui.showExit();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Bye. Hope to see you again soon!"));
    }

    @Test
    void showStartGame_printsCorrectOutput() {
        ui.showStartGame();
        String output = outputStream.toString().trim();
        assertTrue(output.contains("Typing test started! Type the following text:"));
    }
}
