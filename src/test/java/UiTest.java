import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    void setUp() {
        ui = new Ui();
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

