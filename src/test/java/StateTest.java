import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateTest {

    private State state;
    private final String testFilePath = "data/testBoboType.txt";
    private static final Logger logger = Logger.getLogger(StateTest.class.getName());

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();

        state = new State();
        logger.info("Set up complete. Temporary file created and State instance initialized.");
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file after each test
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        logger.info("Tear down complete. Temporary file deleted.");
    }

    @Test
    public void testGetHighScore() {
        assertEquals(0.0, state.getHighScore());
        logger.info("testGetHighScore passed.");
    }

    @Test
    public void testUpdateHighScore() {
        state.updateHighScore(100.0);
        assertEquals(100.0, state.getHighScore());
        logger.info("High score updated to 100.0 and verified.");

        state.updateHighScore(50.0);
        assertEquals(100.0, state.getHighScore());
        logger.info("High score remains 100.0 after attempting to update with a lower score.");

        state.updateHighScore(150.0);
        assertEquals(150.0, state.getHighScore());
        logger.info("High score updated to 150.0 and verified.");
    }
}