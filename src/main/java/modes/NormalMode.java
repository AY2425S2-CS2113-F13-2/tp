package modes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import storage.TypingTargets;
import ui.Ui;
import util.WordCounter;
import storage.AutoAdjust;
import storage.State;
import typing.TypingAccuracy;
import typing.TypingTarget;
import typing.TypingTargetList;
import typing.TypingTargetSpeed;
import typing.TypingTargetScore;


public class NormalMode {
    private final TypingAccuracy typingAccuracy;
    private final TypingTimer typingTimer;
    private final Scanner sc;
    private final Ui ui;
    private final TypingTargetList typingTargetList;
    private final TypingTargets typingTargets;
    private final State state;
    private final AutoAdjust autoAdjust;

    public NormalMode(Ui ui, Scanner sc, TypingTargetList typingTargetList, TypingTargets typingTargets,
                      State state, AutoAdjust autoAdjust,
                      TypingAccuracy typingAccuracy) {
        this.ui = ui;
        this.sc = sc;
        this.typingTargetList = typingTargetList;
        this.typingTargets = typingTargets;
        this.state = state;
        this.autoAdjust = autoAdjust;
        this.typingTimer = new TypingTimer();
        this.typingAccuracy = typingAccuracy;
    }

    public void startNormalMode(List<String> testText) throws IOException {
        typingAccuracy.setTestText((ArrayList<String>) testText);
        ui.showStartGame();
        int wordCount = 0;
        int characterCount = 0;

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
        typingTargets.update(typingTargetList);

        // Adjust the game based on typing speed
        autoAdjust.evaluate((int) (wordCount / duration));

        // Update the high score
        try {
            state.updateHighScore(typingAccuracy.getTypingAccuracy(), (int) (wordCount / duration));
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
