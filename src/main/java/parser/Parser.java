package parser;

import exceptions.BoboTypeException;
import exceptions.FileProcessingException;
import exceptions.InvalidInputException;
import modes.TimeLimitMode;
import modes.TypingTimer;
import modes.ZenMode;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.Storage;
import typing.*;
import ui.Ui;
import util.RandNumGenerator;
import util.WordCounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private String userInput;
    private String[] inputSplit;

    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Constructs a UserInputParser object and sets the user input
     * Splits user input by spaces
     * @param userInput The complete input provided by the user
     */
    public Parser(String userInput) {
        this.userInput = userInput;
        this.inputSplit = userInput.split(" ");
    }

    public static void handleCommand(String[] inputParts, Ui ui, Scanner sc, Milestones milestones, TypingTimer typingTimer, TypingAccuracy typingAccuracy,
                                     TypingTargetList typingTargetList, State state, AutoAdjust autoAdjust) throws IOException {
        final int NUM_OF_TEXTS = 3;
        int wordCount = 0;
        int characterCount = 0;

        String command = inputParts[0];

        switch (command) {
        case "start":
            ui.chooseMode();
            String mode = sc.nextLine().trim();
            if (mode.equals("zen")) {
                ZenMode zenMode = new ZenMode(typingTimer, sc, ui);
                zenMode.startZenMode();
            } else {
                // select difficulty and length of the test
                List<String> testText;
                String difficultyLevel;
                String textLength;
                while (true) {
                    try {
                        // load difficulty from milestones.txt
                        difficultyLevel = milestones.getCurrentDifficulty();
                        ui.showDefaultDifficultyPrompt(difficultyLevel);

                        String input = sc.nextLine().trim();
                        if (input.equalsIgnoreCase("override")) {
                            ui.chooseDifficulty();
                            difficultyLevel = sc.nextLine().trim();
                        }

                        ui.chooseLength();
                        textLength = sc.nextLine().trim();

                        int randomNum = RandNumGenerator.randInt(1, NUM_OF_TEXTS);

                        testText = TextSelector.selectText(difficultyLevel, textLength, randomNum);
                        break;
                    } catch (InvalidInputException | FileProcessingException e) {
                        ui.showErrorMessage(e.getMessage());
                    }
                }
                // time limit mode
                if (mode.equals("timeLimit")) {
                    int timeLimit;
                    int numOfLines;
                    int numOfCorrect;
                    TimeLimitMode timeLimitMode = new TimeLimitMode();
                    ui.showTimeLimitModeInstructions();

                    try {
                        timeLimitMode.run(testText, difficultyLevel);
                    } catch (InterruptedException e) {
                        ui.showErrorMessage(e.getMessage());
                    }
                    numOfLines = testText.size();
                    numOfCorrect = timeLimitMode.getNumOfCorrect();
                    ui.showTimeLimitResult(numOfLines, numOfCorrect);
                    sc.nextLine(); // to clear the input
                } else { // normal mode
                    typingAccuracy.setTestText((ArrayList<String>) testText);
                    ui.showStartGame();
                    wordCount = 0;
                    characterCount = 0;

                    typingTimer.start();

                    for (String s : testText) {
                        System.out.println(s);
                        String userInput = sc.nextLine();
                        typingAccuracy.updateUserInput(userInput);
                        wordCount += WordCounter.countWords(userInput);
                        characterCount += userInput.length();
                    }
                    typingTimer.stop();

                    ui.showResult();
                    double duration = typingTimer.getDurationMin();
                    int typingSpeedWPM = (int) (wordCount / duration);
                    int typingSpeedCPM = (int) (characterCount / duration);
                    double typingAccuracyDouble = typingAccuracy.getTypingAccuracy();
                    double typingScore = (double) typingSpeedWPM * typingAccuracyDouble;
                    ui.showTypingSpeedWPM(typingSpeedWPM);
                    ui.showTypingSpeedCPM(typingSpeedCPM);
                    // ui.showTypingAccuracy(typingAccuracyDouble);
                    ui.showTypingScore(typingScore);

                    for (TypingTarget typingTarget : typingTargetList.getTypingTargetList()) {
                        if (typingTarget instanceof TypingTargetSpeed) {
                            if (typingSpeedWPM >= typingTarget.getTarget()) {
                                typingTarget.setHit(true);
                            }
                            typingTarget.printHit();
                        } else if (typingTarget instanceof TypingTargetScore) {
                            if (typingScore >= typingTarget.getTarget()) {
                                typingTarget.setHit(true);
                            }
                            typingTarget.printHit();
                        }
                    }

                    double time = typingTimer.getDurationMin();
                    state.updateHighScore(typingAccuracy.getTypingAccuracy(), (int) (wordCount / time));
                    autoAdjust.evaluate(state.getHighScore());
                }

                ui.showEndGame();
            }
            break;

        case "typingaccuracy":
            try {
                ui.showTypingAccuracy(typingAccuracy.getTypingAccuracy());
            } catch (BoboTypeException e) {
                ui.showErrorMessage(e.getMessage());
            }
            break;

        case "highscore":
            ui.showHighScore();
            break;

        case "highscorelist":
            ui.showHighScoreList();
            break;

        case "milestone":
            String difficulty = milestones.getCurrentDifficulty();
            ui.showCurrentMilestone(difficulty);
            break;

        case "targetspeedadd":
            System.out.println("Please enter a typing speed target you would like to hit (WPM)!");
            try {
                String targetSpeed = sc.nextLine().trim();
                long targetSpeedLong = Long.parseLong(targetSpeed);
                typingTargetList.addTarget(new TypingTargetSpeed(targetSpeedLong));
                typingTargetList.print();
            } catch (NumberFormatException e) {
                System.out.println("Invalid target speed entered. Please provide a valid integer!");
            }
            break;

        case "targetscoreadd":
            System.out.println("Please enter a typing score target you would like to hit (effective WPM)!");
            try {
                String targetScore = sc.nextLine().trim();
                long targetScoreLong = Long.parseLong(targetScore);
                typingTargetList.addTarget(new TypingTargetScore(targetScoreLong));
                typingTargetList.print();
            } catch (NumberFormatException e) {
                System.out.println("Invalid target score entered. Please provide a valid integer!");
            }
            break;

        default:
            ui.drawLine();
            System.out.println("Invalid command entered. Please provide a valid input!");
            ui.drawLine();
        }
    }


}

