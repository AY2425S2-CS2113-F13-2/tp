/**
 * Class to load and save typing targets to local storage
 */

package storage;

import typing.TypingTarget;
import typing.TypingTargetList;
import typing.TypingTargetScore;
import typing.TypingTargetSpeed;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TypingTargets {
    private static File file;

    /**
     * Constructs a Milestones object that tracks user progress.
     *
     * @param filePath The path to the milestones file used to store progress.
     */
    public TypingTargets(String filePath) {
        file = new File(filePath);
    }

    /**
     * Clears the file by writing an empty string to it
     *
     * @throws IOException if an input/output operation has failed
     */
    public static void clear() throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write("");
        fw.close();
    }

    /**
     * Loads the milestone progress from the file.
     * If the file doesn't exist, it will be initialized with default values.
     *
     * @param typingTargetList Typing target list
     * @throws IOException if there was an error reading the file
     */
    public void load(TypingTargetList typingTargetList) throws IOException {
        if (!file.exists()) {
            clear(); // Save defaults if file doesn't exist
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                String[] data = line.split("\\|");
                if (data[0].equals("Speed")) {
                    typingTargetList.addTarget(
                            new TypingTargetSpeed(Long.parseLong(data[1]),
                                    Boolean.parseBoolean(data[2]))
                    );
                } else {
                    typingTargetList.addTarget(
                            new TypingTargetScore(Long.parseLong(data[1]),
                                    Boolean.parseBoolean(data[2]))
                    );
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading typing targets file.");
        }
    }

    /**
     * Saves the current typing target list to the file
     *
     * @param typingTargetList Typing target list
     * @throws IOException if an input/output operation has failed
     */
    public void update(TypingTargetList typingTargetList) throws IOException {
        try (FileWriter fw = new FileWriter(file)) {
            ArrayList<TypingTarget> typingTargetList1 = typingTargetList.getTypingTargetList();
            for (TypingTarget typingTarget : typingTargetList1) {
                if (typingTarget instanceof TypingTargetSpeed) {
                    fw.write("Speed|" + typingTarget.getTarget() + "|" + typingTarget.getHit() + "\n");
                } else {
                    fw.write("Score|" + typingTarget.getTarget() + "|" + typingTarget.getHit() + "\n");
                }
            }
        }
    }
}
