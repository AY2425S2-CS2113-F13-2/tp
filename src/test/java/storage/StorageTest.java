import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private Storage storage;
    private final String testFilePath = "data/testBoboType.txt";

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
        System.out.println("Setup complete. Test file path: " + testFilePath);
    }

    @AfterEach
    public void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) {
            file.delete();
        }
        System.out.println("Teardown complete. Test file deleted.");
    }

    @Test
    public void testSaveScoreList() throws IOException {
        ArrayList<Double> scores = new ArrayList<>();
        scores.add(123.45);
        scores.add(678.90);
        storage.saveScoreList(scores);

        File file = new File(testFilePath);
        assertTrue(file.exists(), "File should exist after saving scores.");

        Scanner scanner = new Scanner(file);
        ArrayList<Double> savedScores = new ArrayList<>();
        while (scanner.hasNextDouble()) {
            savedScores.add(scanner.nextDouble());
        }
        scanner.close();

        assertEquals(scores, savedScores, "Saved scores should match the input scores.");
        System.out.println("testSaveScoreList passed. Saved scores: " + savedScores);
    }

    @Test
    public void testEmptyFile() throws IOException {
        File file = new File(testFilePath);
        file.createNewFile(); // Create an empty file

        ArrayList<Double> readScores = storage.readHighScoreList();
        assertTrue(readScores.isEmpty(), "Read scores from an empty file should be an empty list.");
        System.out.println("testEmptyFile passed. Read scores: " + readScores);
    }

    @Test
    public void testInvalidData() throws IOException {
        FileWriter writer = new FileWriter(testFilePath);
        writer.write("invalid data\n");
        writer.close();

        ArrayList<Double> readScores = storage.readHighScoreList();
        assertTrue(readScores.isEmpty(), "Read scores from a file with invalid data should be an empty list.");
        System.out.println("testInvalidData passed. Read scores: " + readScores);
    }
}
