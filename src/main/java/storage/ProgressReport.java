package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ui.Ui;

/**
 * Tracks and displays the user's past typing progress.
 * Stores the last 10 session scores and visualizes them in a CLI-friendly bar graph.
 */
public class ProgressReport {
    private static final int MAX_ENTRIES = 10;
    private final File file;
    private final ArrayList<Integer> scores;
    private final Ui ui;
    private int sessionCounter;

    /**
     * Constructs a ProgressReport instance tied to a file path.
     * Loads existing progress from the file or initializes a new file.
     *
     * @param filePath Path to the progress report file.
     * @param ui The Ui instance used to show messages.
     */
    public ProgressReport(String filePath, Ui ui) {
        this.file = new File(filePath);
        this.scores = new ArrayList<>();
        this.ui = ui;
        this.sessionCounter = 0;
        load();
    }

    /**
     * Loads session counter and scores from the file.
     * If the file doesn't exist, a new file is initialized.
     * Displays a recovery message if file is corrupted.
     */
    private void load() {
        scores.clear();
        if (!file.exists()) {
            save();
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                String firstLine = scanner.nextLine().trim();
                if (firstLine.startsWith("counter:")) {
                    sessionCounter = Integer.parseInt(firstLine.split(":")[1].trim());
                }
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    scores.add(Integer.parseInt(line));
                }
            }
        } catch (Exception e) {
            System.err.println("Error reading progress file. File might be corrupted. " +
                    "Please delete data/progress.txt and restart the program!");
        }
    }

    /**
     * Saves the session counter and all stored scores to file.
     * Overwrites the file contents.
     */
    private void save() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("counter:" + sessionCounter + "\n");
            for (Integer score : scores) {
                writer.write(score + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving progress report: " + e.getMessage());
        }
    }

    /**
     * Updates the report with the latest typing score.
     * Keeps only the most recent 10 scores.
     *
     * @param score The new score (WPM * accuracy) to record.
     */
    public void update(double score) {
        int intScore = (int) score;
        sessionCounter++;

        if (scores.size() >= MAX_ENTRIES) {
            scores.remove(0);
        }
        scores.add(intScore);
        save();
    }

    /**
     * Displays the past 10 WPM scores in a simple text-based bar chart.
     */
    public void show() {
        if (scores.isEmpty()) {
            ui.showErrorMessage("No past data found!");
            ui.drawLine();
            return;
        }

        System.out.println("Typing Progress (Past " + scores.size() + " sessions):");
        System.out.println("Each '█' represents 5 points");

        int startIdx = Math.max(0, scores.size() - MAX_ENTRIES); // Only keep last 10
        for (int i = scores.size() - 1; i >= startIdx; i--) {
            int sessionNumber = sessionCounter - (scores.size() - i - 1);
            int score = scores.get(i);
            String bar = "█".repeat(score / 5);
            System.out.printf("Session %2d: %-60s (%d pts)%n", sessionNumber, bar, score);
        }

        ui.drawLine();
    }

    // Helper Methods for Testing
    public int getSessionCounter() {
        return sessionCounter;
    }

    public int getScoreCount() {
        return scores.size();
    }

    public int getLastScore() {
        return scores.get(scores.size() - 1);
    }

}
