package modes;

import storage.InputUserText;
import typing.TypingAccuracy;
import ui.Ui;
import util.WordCounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomMode {

    private final Ui ui;
    private final Scanner scanner;
    private final TypingTimer typingTimer;
    private final TypingAccuracy typingAccuracy;

    public CustomMode(Ui ui, Scanner scanner, TypingAccuracy typingAccuracy) {
        this.ui = ui;
        this.scanner = scanner;
        this.typingTimer = new TypingTimer();
        this.typingAccuracy = typingAccuracy;
    }

    public void startCustomMode() throws IOException {
        InputUserText inputUserText = new InputUserText();
        List<String> customText = inputUserText.inputText();

        typingAccuracy.setTestText((ArrayList<String>) customText);
        //ui.showStartGame();

        int wordCount = 0;
        int characterCount = 0;

        for (String s : customText) {
            System.out.println(s);
            String userInput = scanner.nextLine();
            typingAccuracy.updateUserInput(userInput);
            wordCount += WordCounter.countWords(userInput);
            characterCount += userInput.length();
        }

        double duration = typingTimer.getDurationMin();
        int typingSpeedWPM = (int) (wordCount / duration);
        int typingSpeedCPM = (int) (characterCount / duration);
        double typingAccuracyDouble = typingAccuracy.getTypingAccuracy();
        double typingScore = (double) typingSpeedWPM * typingAccuracyDouble;

        ui.showTypingSpeedWPM(typingSpeedWPM);
        ui.showTypingSpeedCPM(typingSpeedCPM);
        ui.showTypingScore(typingScore);
    }
}