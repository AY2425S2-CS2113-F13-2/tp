package storage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProgressReportTest {
    private static final String TEST_FILE_PATH = "data/testProgress.txt";
    private ProgressReport progressReport;
    private Ui ui;

    @BeforeEach
    public void setup() {
        ui = new Ui(null);
        progressReport = new ProgressReport(TEST_FILE_PATH, ui);
    }

    @AfterEach
    public void cleanup() {
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testUpdateAndLoadProgress() {
        progressReport.update(75.0);
        progressReport = new ProgressReport(TEST_FILE_PATH, ui);
        assertEquals(1, progressReport.getScoreCount());
        assertEquals(75, progressReport.getLastScore());
    }

    @Test
    public void testSessionCounterIncrement() throws IOException {
        int initialCounter = progressReport.getSessionCounter();
        progressReport.update(50.0);
        progressReport.update(80.0);
        ProgressReport reloaded = new ProgressReport(TEST_FILE_PATH, ui);
        assertEquals(initialCounter + 2, reloaded.getSessionCounter());
    }

    @Test
    public void testMaxEntriesTrimOld() {
        for (int i = 0; i < 15; i++) {
            progressReport.update(i * 10.0);
        }
        progressReport = new ProgressReport(TEST_FILE_PATH, ui);
        assertEquals(10, progressReport.getScoreCount());
        assertEquals(140, progressReport.getLastScore());
    }
}
