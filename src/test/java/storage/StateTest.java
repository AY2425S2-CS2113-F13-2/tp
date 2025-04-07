package storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StateTest {

    private State state;
    private Storage storage;
    private final String testFilePath = "data/testBoboType.txt";

    @BeforeEach
    public void setUp() throws IOException {
        // Create a temporary file for testing
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        file.getParentFile().mkdirs();
        file.createNewFile();

        // Write an initial value to the file to avoid NoSuchElementException
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("0.00");
        } catch (Exception e) {
            System.err.println("Error writing to temporary file.");
        }

        storage = new Storage(testFilePath);
        state = new State(storage);
        System.out.println("Set up complete. Temporary file created and State instance initialized.");
    }

    @AfterEach
    public void tearDown() {
        // Delete the temporary file after each test
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("Tear down complete. Temporary file deleted.");
    }

    @Test
    public void testGetHighScore() {
        assertEquals(0.00, state.getHighScore());
        System.out.println("testGetHighScore passed.");
    }

    @Test
    public void testUpdateHighScore() throws IOException {
        state.updateHighScore(1.0, 100);
        assertEquals(100.00, state.getHighScore());
        System.out.println("High score updated to 100.0 and verified.");

        state.updateHighScore(1.0, 50);
        assertEquals(100.00, state.getHighScore());
        System.out.println("High score remains 100.0 after attempting to update with a lower score.");

        state.updateHighScore(1.0, 150);
        assertEquals(150.00, state.getHighScore());
        System.out.println("High score updated to 150.0 and verified.");
    }

    @Test
    public void testGetHighScoreList() throws IOException {
        state.updateHighScore(1.0, 100);
        state.updateHighScore(1.0, 200);
        state.updateHighScore(1.0, 300);

        ArrayList<Double> highScoreList = state.getHighScoreList();
        assertEquals(3, highScoreList.size(), "There should be 3 scores in the list.");
        assertEquals(300.00, highScoreList.get(0), "First score should be the highest.");
        assertEquals(200.00, highScoreList.get(1), "Second score should be the second highest.");
        assertEquals(100.00, highScoreList.get(2), "Third score should be the third highest.");
        System.out.println("testGetHighScoreList passed. Scores: " + highScoreList);
    }
}
