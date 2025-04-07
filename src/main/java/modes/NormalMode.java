package modes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import storage.TypingTargets;
import typing.MarkedText;
import typing.TypingAccuracy;
import typing.TypingTarget;
import typing.TypingTargetSpeed;
import typing.TypingTargetScore;
import typing.TypingTargetList;
import ui.Ui;
import util.WordCounter;
import storage.AutoAdjust;
import storage.State;
import storage.ProgressReport;


public class NormalMode {
    private final TypingAccuracy typingAccuracy;
    private final TypingTimer typingTimer;
    private final Scanner sc;
    private final Ui ui;
    private final TypingTargetList typingTargetList;
    private final TypingTargets typingTargets;
    private final State state;
    private final AutoAdjust autoAdjust;
    private final ProgressReport progressReport;

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
        this.progressReport = new ProgressReport("data/progress.txt", ui);
    }

    public void startNormalMode(List<String> testText, String difficultyLevel) throws IOException {
        typingAccuracy.clearUserText();
        typingAccuracy.setTestText((ArrayList<String>) testText);
        int wordCount = 0;
        int characterCount = 0;

        // Start test
        ui.countdown(ui);
        typingTimer.start();
        ui.clearScreen(ui);

        // Typing test logic
        MarkedText markedText = new MarkedText();
        for (String s : testText) {
            ui.showString(s);
            String userInput = sc.nextLine();
            markedText.update(s, userInput);

            typingAccuracy.updateUserInput(userInput);
            wordCount += WordCounter.countWords(userInput);
            characterCount += userInput.length();

            ui.clearScreen(ui);
            markedText.print();
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

        // Update the high score
        try {
            state.updateHighScore(typingAccuracy.getTypingAccuracy(), (int) (wordCount / duration));
            autoAdjust.evaluate(difficultyLevel, typingScore);
            progressReport.update(typingScore);

        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }

    }
}
