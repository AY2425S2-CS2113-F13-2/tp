import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Storage storage;
    private final String testFilePath = "data/testBoboType.txt";

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testSaveScoreList() throws IOException {
        Double score = 123.45;
        storage.saveScoreList(score);

        File file = new File(testFilePath);
        assertTrue(file.exists());

        Scanner scanner = new Scanner(file);
        Double savedScore = scanner.nextDouble();
        scanner.close();

        assertEquals(score, savedScore);
    }

    @Test
    public void testReadHighScoreFromFile() throws IOException {
        Double score = 678.90;
        storage.saveScoreList(score);

        Double readScore = storage.readHighScoreFromFile();
        assertEquals(score, readScore);
    }
}
