package storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import ui.Ui;

public class ProgressReport {
    private static final int MAX_ENTRIES = 10;
    private final File file;
    private final ArrayList<Integer> scores;
    private final Ui ui;
    private int sessionCounter;

    public ProgressReport(String filePath, Ui ui) {
        this.file = new File(filePath);
        this.scores = new ArrayList<>();
        this.ui = ui;
        this.sessionCounter = 0;
        load();
    }

    /**
     * Loads the progress report from the file.
     * If the file doesn't exist, it will be initialized with default values.
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
        } catch (IOException e) {
            System.err.println("Unable to load progress report: " + e.getMessage());
        }
    }

    /**
     * Saves the latest progress to the file.
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
            ui.drawLine();
            ui.showErrorMessage("No past data found!");
            ui.drawLine();
            return;
        }

        ui.drawLine();
        System.out.println(" Typing Progress (Past " + scores.size() + " sessions):");
        System.out.println(" Each '█' represents 5 points");

        int startIdx = Math.max(0, scores.size() - MAX_ENTRIES); // Only keep last 10
        for (int i = scores.size() - 1; i >= startIdx; i--) {
            int sessionNumber = sessionCounter - (scores.size() - i - 1);
            int score = scores.get(i);
            String bar = "█".repeat(score / 5);
            System.out.printf(" Session %2d: %-60s (%d pts)%n", sessionNumber, bar, score);
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
