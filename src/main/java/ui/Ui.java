package ui;

import storage.State;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private final State state;

    public Ui(State state) {
        this.state = state;
    }

    public void drawLine() {
        System.out.println("____________________________________________________________");
    }

    public String readInput(Scanner scanner) {
        return scanner.nextLine();
    }

    public void showWelcome() {
        drawLine();
        System.out.println(" Welcome to BoboType!");
        System.out.println(" Type 'start' to start the game.");
        drawLine();
    }

    public void showExit() {
        drawLine();
        System.out.println(" Bye. Hope to see you again soon!");
        drawLine();
    }

    public void chooseMode() {
        drawLine();
        System.out.println(" Select your mode: ");
        System.out.println(" Type: 'normal' or 'timeLimit' or 'zen' or 'custom'");
        drawLine();
    }

    public void chooseDifficulty() {
        drawLine();
        System.out.println(" Select your difficulty: ");
        System.out.println(" Type: 'easy' or 'intermediate' or 'difficult'");
        drawLine();
    }

    public void chooseLength() {
        drawLine();
        System.out.println(" Select your text length: ");
        System.out.println(" Type: 'short' or 'medium' or 'long'");
        drawLine();
    }

    public void showErrorMessage(String message) {
        System.out.println(" *** Oops! *** \n -> " + message);
    }

    public void showResult() {
        drawLine();
        System.out.println(" Hope you enjoyed the round! Here are your stats:");
    }

    public void showTypingSpeedWPM(int typingSpeedWPM) {
        System.out.println(" Typing speed (WPM): " + typingSpeedWPM + " WPM");
    }

    public void showTypingSpeedCPM(int typingSpeedCPM) {
        System.out.println(" Typing speed (CPM): " + typingSpeedCPM + " CPM");
    }

    public void showTypingScore(double typingScore) {
        System.out.print(" Typing score (Effective WPM): ");
        System.out.printf("%.2f", typingScore);
        System.out.println(" WPM");
    }

    /**
     * Displays typing accuracy of user in percentage to user
     *
     * @param typingAccuracy Typing Accuracy of user in decimal (0.0 to 1.0)
     */
    public void showTypingAccuracy(double typingAccuracy) {
        System.out.print(" Typing accuracy: ");
        System.out.printf("%.2f", typingAccuracy * 100);
        System.out.println("%");
    }

    public void showHighScore() {
        drawLine();
        System.out.println(" Your high score is: " + state.getHighScore());
        drawLine();
    }

    public void showHighScoreList() {
        drawLine();
        System.out.println(" Top 3 High Scores: ");
        ArrayList<Double> highScoreList = state.getHighScoreList();
        for (int i = 1; i <= Math.min(4, highScoreList.size()); i++) {
            System.out.println(" " + i + ". " + highScoreList.get(i - 1));
        }
        drawLine();
    }

    public void showTargetAdded(String targetString) {
        drawLine();
        System.out.println(" Target added!");
        System.out.println(targetString);
        drawLine();
    }

    public void showTargetRemoved(String targetString) {
        drawLine();
        System.out.println(" Target removed!");
        System.out.println(targetString);
        drawLine();
    }

    public void showEndGame() {
        drawLine();

        System.out.println("""
                 You finished the practice! Please type\s
                \t - 'highscore' to view your high score\s
                \t - 'highscore list' to view your top 3 high scores\s
                \t - 'milestone' to view your default difficulty level\s
                \t - 'target list' to view your typing targets\s
                \t - 'target add speed SPEED' to add a typing speed target\s
                \t - 'target add score SCORE' to add a typing score target\s
                \t - 'target remove TARGET_INDEX' to remove a typing target\s
                \t - 'progress' to view your typing progress\s
                \t - 'exit' to exit or\s
                \t - 'start' to start the new practice.""");
        drawLine();

    }

    public void showZenModeInstructions() {
        drawLine();
        System.out.print("""
                 Welcome to Zen Mode, you can type out anything to your
                 heart's content and find out your typing speed.
                 Typing 'start' on a new line will start the typingTimer and typing the command
                 'stop_practice' on a new line will stop the practice.
                """);
        drawLine();
    }

    public void showZenModeStartPrompt() {
        drawLine();
        System.out.println(" Please type 'start' on a new line to start Zen mode");
        drawLine();
    }

    public void showZenModeEndGame(int wordCount, int typingSpeedWPM, int typingSpeedCPM) {
        drawLine();
        String wordText = " word";
        if (wordCount > 1) {
            wordText = " words";
        }
        System.out.println(" You finished the Zen Mode Practice! \n" +
                " You have typed: " + wordCount + wordText);
        showTypingSpeedWPM(typingSpeedWPM);
        showTypingSpeedCPM(typingSpeedCPM);
        drawLine();
        System.out.println("""
                     Please type\s
                    \t - 'exit' to exit or\s
                    \t - 'start' to start the new practice.""");
        drawLine();

    }

    public void showTimeLimitModeInstructions(Ui ui) {
        drawLine();
        System.out.println(" Welcome to timeLimit mode.");
        System.out.println(" *** In this mode, you can view your input ONLY after you finish your sentence ***");
        System.out.println(" *** i.e when ENTER is pressed!!! ***");
        drawLine();
        sleep(ui);
        System.out.println(" Are you ready? The game will begin in...");
        countdown(ui);
    }
    public void showTimeLimitMiddleMessage() {
        System.out.println(" *** Press enter to continue... " +
                "Previous input (shown below, if any) will be cleared. ***");
    }
    public void showTimeLimitResult(int numOfLines, int numOfCorrect) {
        System.out.println();
        drawLine();
        System.out.println(" You finished the timeLimit Mode Practice!");
        System.out.println("\t - Num of correct lines: " + numOfCorrect + " lines out of " + numOfLines + " lines");
        drawLine();
        System.out.println(" *** Please press enter to continue. ***");
    }

    public void showMilestoneAchieved(String difficulty, int wpmGoal) {
        drawLine();
        System.out.println(" Congrats! You hit the milestone:");
        System.out.println(" -> Achieved a score of " + wpmGoal + " in " + difficulty + " mode!");
        System.out.println(" You've been promoted to a new difficulty level.");
    }

    public void showFinalMilestoneAchieved(int wpmGoal) {
        drawLine();
        System.out.println(" Congrats! You have completed the max difficulty level by hitting a score of " + wpmGoal +
                " in difficult mode!");
    }

    public void showDefaultDifficultyPrompt(String difficultyLevel) {
        drawLine();
        System.out.println(" Default difficulty: " + difficultyLevel);
        System.out.println(" (Type 'override' to choose your own difficulty, or leave blank to proceed)");
        drawLine();
    }

    public void showCurrentMilestone(String difficulty) {
        drawLine();
        System.out.println(" Current milestone: " + difficulty);
        drawLine();
    }

    private static void sleep(Ui ui) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            ui.drawLine();
            ui.showErrorMessage(e.getMessage());
            ui.drawLine();
        }
    }

    public void countdown(Ui ui) {
        drawLine();
        for (int countdown = 3; countdown > 0; countdown--) {
            System.out.println(" " + countdown);
            sleep(ui);
        }
        System.out.println(" Start!");
        drawLine();
        sleep(ui);
    }

    public void clearScreen(Ui ui) {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            ui.drawLine();
            ui.showErrorMessage(e.getMessage());
            ui.drawLine();
        }
    }

    public void showString(String s) {
        System.out.println(s);
    }

    public void showCustomMode() {
        System.out.println(" We will start the Custom Mode Typing Test now.");
    }

    public void showInputUserText() {
        drawLine();
        System.out.println(" Please type your custom text. Press 'Enter' then type 'exit' to finish.");
        drawLine();
    }

    public void showTextSaved() {
        System.out.println(" Your custom text has been saved.");
    }
}


