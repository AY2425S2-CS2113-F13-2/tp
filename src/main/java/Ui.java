public class Ui {

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

    public void chooseDifficulty() {
        drawLine();
        System.out.println(" Select your difficulty: ");
        System.out.println(" Type: 'easy' or 'intermediate' or 'difficult'");
        drawLine();
    }

    public void showErrorMessage(String message) {
        System.out.println("[Error] " + message);
    }

    public void showResult() {
        drawLine();
        System.out.println(" Hope you enjoyed the round! Here are your stats:");
    }

    public void showTypingAccuracy(double typeAccuracy) {
        drawLine();
        System.out.println(" Your typing accuracy is: " + typeAccuracy*100 + "%");
        drawLine();
    }

    public void showHighScore() {
        drawLine();
        System.out.println(" Your high score is: :");
        drawLine();
    }

    public void showStartGame() {
        drawLine();
        System.out.println("Typing test started! Type the following text:");
    }

    public void showEndGame() {
        drawLine();
        System.out.println("You finished the practice! Please type \n" +
                "\t - 'result' to view the result \n" +
                "\t - 'typingaccuracy' to view your typing accuracy \n" +
                "\t - 'exit' to exit or \n" +
                "\t - 'start' to start the new practice.");
        drawLine();

    }
}


