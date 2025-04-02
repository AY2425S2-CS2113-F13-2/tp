package util;

import ui.Ui;

import exceptions.FileProcessingException;
import storage.Milestones;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import enums.TextLength;
import enums.DifficultyLevel;

public class TextSelector {
    private static Logger logger = setupLogger();
    private final Milestones milestones;
    private final int NUM_OF_TEXTS = 3;
    private List<String> testText;
    private FileReader fileReader;
    private Scanner sc;
    private Ui ui;
    private DifficultyLevel difficultyLevel;
    private TextLength textLength;

    public TextSelector(Scanner sc, Ui ui) {
        milestones = new Milestones("data/milestones.txt");
        fileReader = new FileReader();
        this.sc = sc;
        this.ui = ui;
    }

    private static Logger setupLogger() {
        Logger logger = Logger.getLogger("TextSelectorLogger");
        logger.setLevel(Level.WARNING);
        return logger;
    }

    public DifficultyLevel selectDifficulty() {
        String defaultDifficulty = milestones.getCurrentDifficulty().toUpperCase();
        difficultyLevel = DifficultyLevel.valueOf(defaultDifficulty);
        ui.showDefaultDifficultyPrompt(difficultyLevel.name().toLowerCase());

        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("override")) {
            while (true) {
                ui.chooseDifficulty();
                input = sc.nextLine().trim().toUpperCase();
                try {
                    difficultyLevel = DifficultyLevel.valueOf(input);
                    break;
                } catch (IllegalArgumentException e) {
                    ui.showErrorMessage("Please enter a valid difficulty level.");
                }
            }
        }
        return difficultyLevel;
    }

    public TextLength selectLength() {
        while (true) {
            ui.chooseLength();
            String input = sc.nextLine().trim().toUpperCase();
            try {
                textLength = TextLength.valueOf(input);
                break;
            } catch (IllegalArgumentException e) {
                ui.showErrorMessage("Please enter a valid text length.");
            }
        }
        return textLength;
    }

    public List<String> selectText() {
        selectDifficulty();
        selectLength();
        int randomNum = RandNumGenerator.randInt(1, NUM_OF_TEXTS);
        String difficultyLevelName = difficultyLevel.name().toLowerCase();
        String textLengthName = textLength.name().toLowerCase();
        String filePath = "/sample_texts/" + difficultyLevelName + "/" + textLengthName + "/" + randomNum + ".txt";
        try {
            testText = fileReader.readFile(filePath);
        } catch (Exception e) {
            ui.showErrorMessage("An error occurred while retrieving text. Please try again later.");
            logger.log(Level.SEVERE, "Error reading file: " + filePath, e);
            throw new FileProcessingException("There has been error when reading file from " + filePath);
        }
        return testText;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }
}

