import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles milestone tracking and progression logic.
 * Reads and writes milestone progress to a file.
 */
public class Milestones {
    private final File file;
    private String currentDifficulty = "easy";
    private final List<String> achieved = new ArrayList<>();
    private final List<String> difficulties = List.of("easy", "intermediate", "difficult");

    /**
     * Constructs a Milestones object that tracks user progress.
     *
     * @param filePath The path to the milestones file used to store progress.
     */
    public Milestones(String filePath) {
        this.file = new File(filePath);
        load();
    }

    /**
     * Loads the milestone progress from the file.
     * If the file doesn't exist, it will be initialized with default values.
     */
    private void load() {
        if (!file.exists()) {
            save(); // Save defaults if file doesn't exist
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("currentDifficulty:")) {
                    currentDifficulty = line.split(":")[1].trim();
                } else if (!line.isEmpty()) {
                    achieved.add(line); // Assume it's a difficulty name
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading milestone file.");
        }
    }

    /**
     * Saves the current milestone progress to the file.
     */
    private void save() {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("currentDifficulty:" + currentDifficulty + "\n");
            for (String diff : achieved) writer.write(diff + "\n");
        } catch (IOException e) {
            System.err.println("Error saving milestone file.");
        }
    }

    /**
     * Checks if the user has achieved the required WPM for a given difficulty.
     * If so, marks it as completed and promotes to the next difficulty level.
     *
     * @param difficulty The difficulty level attempted.
     * @param wpm        The WPM achieved by the user.
     * @return true if a milestone was achieved and updated, false otherwise.
     */
    public boolean checkAndUpdate(String difficulty, double wpm) {
        double goal = switch (difficulty) {
            case "easy" -> 100;
            case "intermediate" -> 120;
            default -> Double.MAX_VALUE;
        };

        if (wpm >= goal && !achieved.contains(difficulty)) {
            achieved.add(difficulty);
            promote(difficulty);
            save();
            return true;
        }
        return false;
    }

    /**
     * Promotes the user to the next difficulty level, if available.
     *
     * @param difficulty The current difficulty level.
     */
    private void promote(String difficulty) {
        int index = difficulties.indexOf(difficulty);
        if (index < difficulties.size() - 1) {
            currentDifficulty = difficulties.get(index + 1);
        }
    }

    /**
     * Gets the user's current difficulty level.
     *
     * @return The current difficulty level.
     */
    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public void setCurrentDifficulty(String difficulty) {
        if (difficulties.contains(difficulty)) {
            currentDifficulty = difficulty;
            save();
        }
    }

    public boolean isAchieved(String difficulty) {
        return achieved.contains(difficulty);
    }
}
