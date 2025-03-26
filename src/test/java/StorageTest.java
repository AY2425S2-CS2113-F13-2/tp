import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    public void testReadHighScoreList() throws IOException {
        ArrayList<Double> scores = new ArrayList<>();
        scores.add(100.0);
        scores.add(200.0);
        scores.add(300.0);
        storage.saveScoreList(scores);

        ArrayList<Double> readScores = storage.readHighScoreList();
        assertEquals(3, readScores.size(), "There should be 3 scores in the list.");
        assertEquals(300.0, readScores.get(0), "First score should be the highest.");
        assertEquals(200.0, readScores.get(1), "Second score should be the second highest.");
        assertEquals(100.0, readScores.get(2), "Third score should be the third highest.");
        System.out.println("testReadHighScoreList passed. Scores: " + readScores);
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
