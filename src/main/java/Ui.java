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

    public void showTypeList() {
        drawLine();
        System.out.println(" Select your difficulty:");
        drawLine();
    }

    public void showResult() {
        drawLine();
        System.out.println(" Hope you enjoyed the round! Here are your stats:");
        drawLine();
    }

    public void showTypingAccuracy() {
        drawLine();
        System.out.println(" Your typing accuracy is: :");
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
}


