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
        System.out.println(" Type: 'normal' or 'timeLimit' or 'zen'");
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

    public void showInvalidInputMessage() {
        drawLine();
        System.out.println("Invalid command entered. Please provide a valid input!");
        drawLine();
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
        System.out.print(" Typing Score (Effective WPM): ");
        System.out.printf("%.2f", typingScore);
        System.out.println(" WPM");
    }

    /**
     * Displays typing accuracy of user in percentage to user
     *
     * @param typingAccuracy Typing Accuracy of user in decimal (0.0 to 1.0)
     */
    public void showTypingAccuracy(double typingAccuracy) {
        drawLine();
        System.out.print(" Your typing accuracy is: ");
        System.out.printf("%.2f", typingAccuracy * 100);
        System.out.println("%");
        drawLine();
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
            System.out.println(i + ". " + highScoreList.get(i - 1));
        }
        drawLine();
    }

    public void showTargetAdded(String targetString) {
        drawLine();
        System.out.println(" Target added!");
        System.out.println(targetString);
        drawLine();
    }

    public void showStartGame() {
        drawLine();
        System.out.println("Typing test started! Type the following text:");
    }

    public void showEndGame() {
        drawLine();

        System.out.println("You finished the practice! Please type \n" +
                "\t - 'typingaccuracy' to view your typing accuracy \n" +
                "\t - 'highscore' to view your high score \n" +
                "\t - 'highscorelist' to view your top 3 high scores \n" +
                "\t - 'milestone' to view your default difficulty level \n" +       
                "\t - 'targetspeedadd' to add a typing speed target \n" +
                "\t - 'targetscoreadd' to add a typing score target \n" +
                "\t - 'exit' to exit or \n" +
                "\t - 'start' to start the new practice.");
        drawLine();

    }

    public void showZenModeInstructions() {
        drawLine();
        System.out.println("Welcome to Zen Mode, you can type out anything to your\n" +
                "heart's content and find out your typing speed.\n" +
                "Typing 'start' will start the typingTimer and typing the command\n" +
                "'stop_practice' will stop the practice.");
        drawLine();
    }

    public void showZenModeEndGame(int wordCount, int typingSpeedWPM, int typingSpeedCPM) {
        drawLine();
        String wordText = " word";
        if (wordCount >1) {
            wordText = " words";
        }
        System.out.println(" You finished the Zen Mode Practice! \n" +
                " You have typed: " + wordCount + wordText);
        showTypingSpeedWPM(typingSpeedWPM);
        showTypingSpeedCPM(typingSpeedCPM);
        drawLine();
        System.out.println("Please type\n" +
                "    - 'exit' to exit or\n" +
                "    - 'start' to start the new practice.");
        drawLine();

    }

    public void showTimeLimitModeInstructions() {
        drawLine();
        System.out.println("Welcome to Time Limit mode.");
        waitOneSec();
        System.out.println("*** You can see your input ONLY after you press ENTER!!! ***");
        waitOneSec();
        System.out.println("*** RESULT will be shown in either GREAT! or WRONG! or Time's up! ***");
        drawLine();
        waitOneSec();
    }

    public void showTimeLimitResult(int numOfLines, int numOfCorrect) {
        waitOneSec();
        System.out.println("");
        drawLine();
        System.out.println("You finished the timeLimit Mode Practice! ");
        System.out.println("\t - Num of correct lines: " + numOfCorrect + " lines out of " + numOfLines + " lines");
        drawLine();
        System.out.println("*** Please press enter to continue. ***");
    }

    private void waitOneSec() {
        try {
            Thread.sleep(1000); // Wait for 1 second (1000 milliseconds)
        } catch (InterruptedException e) {
            System.out.println("Thread was interrupted");
        }
    }

    public void showMilestoneAchieved(String difficulty, int wpmGoal) {
        drawLine();
        System.out.println("Congrats! You hit the milestone:");
        System.out.println(" -> Achieved " + wpmGoal + " WPM in " + difficulty + " mode!");
        System.out.println(" You've been promoted to a new difficulty level.");
    }

    public void showDefaultDifficultyPrompt(String difficultyLevel) {
        drawLine();
        System.out.println(" Default difficulty: " + difficultyLevel);
        System.out.println(" (Type 'override' to choose your own difficulty, or leave blank to proceed)");
        drawLine();
    }

    public void showCurrentMilestone(String difficulty) {
        drawLine();
        System.out.println("Current milestone: " + difficulty);
        drawLine();
    }

    public void showString(String s) {
        System.out.println(s);
    }

}


