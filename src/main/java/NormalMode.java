import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NormalMode {
    private List<String> testText;
    private TypingAccuracy typingAccuracy;
    private TypingTimer typingTimer;
    private int wordCount;
    private int characterCount;
    private Scanner sc;
    private Ui ui;
    private TypingTargetList typingTargetList;
    private State state;
    private AutoAdjust autoAdjust;

    public NormalMode(Ui ui, Scanner sc, TypingTargetList typingTargetList, State state, AutoAdjust autoAdjust, TypingAccuracy typingAccuracy) {
        this.ui = ui;
        this.sc = sc;
        this.typingTargetList = typingTargetList;
        this.state = state;
        this.autoAdjust = autoAdjust;
        this.typingTimer = new TypingTimer();
        this.typingAccuracy = typingAccuracy;
    }

    public void startNormalMode(List<String> testText) {
        typingAccuracy.setTestText((ArrayList<String>) testText);
        ui.showStartGame();
        wordCount = 0;
        characterCount = 0;

        typingTimer.start();

        // Typing test logic
        for (String s : testText) {
            System.out.println(s);
            String userInput = sc.nextLine();
            typingAccuracy.updateUserInput(userInput);
            wordCount += WordCounter.countWords(userInput);
            characterCount += userInput.length();
        }
        typingTimer.stop();

        // Show results
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

        // Handle typing targets
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

        // Adjust the game based on typing speed
        double time = typingTimer.getDurationMin();
        autoAdjust.evaluate((int) (wordCount / time));

        // Update the high score
        try {
            state.updateHighScore(typingAccuracy.getTypingAccuracy(), (int) (wordCount / time));
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
