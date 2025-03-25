public class Ui {

    private State state;

    public Ui(State state) {
        this.state = state;
    }

    public void drawLine() {
        System.out.println("____________________________________________________________");
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

    /**
     * Displays typing accuracy of user in percentage to user
     *
     * @param typeAccuracy Typing Accuracy of user in decimal (0.0 to 1.0)
     */
    public void showTypingAccuracy(double typeAccuracy) {
        drawLine();
        System.out.println(" Your typing accuracy is: " + typeAccuracy * 100 + "%");
        drawLine();
    }

    public void showHighScore() {
        drawLine();
        System.out.println(" Your high score is: " + toString().valueOf(state.getHighScore()));
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
                "\t - 'exit' to exit or \n" +
                "\t - 'start' to start the new practice.");
        drawLine();

    }

    public void showZenModeInstructions() {
        drawLine();
        System.out.println("""
                Welcome to Zen Mode, you can type out anything to your
                heart's content and find out your typing speed.
                Pressing Enter will start the timer and typing the comment
                'stop_practice' will stop the practice.""");
        drawLine();
    }

    public void showZenModeEndGame(int wordCount, int typingSpeedWPM, int typingSpeedCPM) {
        drawLine();
        System.out.println("You finished the Zen Mode Practice! \n" +
                "You have typed: " + wordCount + " words\n");
        showTypingSpeedWPM(typingSpeedWPM);
        showTypingSpeedCPM(typingSpeedWPM);
        drawLine();

    }

    public void showTimeLimitModeInstructions(int timeLimit) {
        drawLine();
        System.out.println("Welcome to Time Limit mode. \n" +
                "\t - You will have " + timeLimit + " seconds to finish typing each sentence.");
        drawLine();
        waitOneSec();
        System.out.println("Are you ready? The game will begin in ");
        waitOneSec();
        System.out.println("3");
        waitOneSec();
        System.out.println("2");
        waitOneSec();
        System.out.println("1");
        drawLine();
        waitOneSec();
    }

    public void showTimeLimitResult(int numOfLines, int numOfCorrect) {
        waitOneSec();
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


}


