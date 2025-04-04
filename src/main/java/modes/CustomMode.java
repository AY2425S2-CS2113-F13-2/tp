package modes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import storage.InputUserText;
import typing.MarkedText;
import typing.TypingAccuracy;
import ui.Ui;
import util.WordCounter;

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
        typingAccuracy.clearUserText();
        InputUserText inputUserText = new InputUserText(ui);
        List<String> customText = inputUserText.inputText();
        typingAccuracy.setTestText((ArrayList<String>) customText);
        int wordCount = 0;
        int characterCount = 0;

        // Start test
        ui.showCustomMode();
        ui.countdown(ui);
        typingTimer.start();
        ui.clearScreen(ui);

        // Typing test logic
        MarkedText markedText = new MarkedText();
        for (String s : customText) {
            ui.showString(s);
            String userInput = scanner.nextLine();
            markedText.update(s, userInput);
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

        ui.showTypingSpeedWPM(typingSpeedWPM);
        ui.showTypingSpeedCPM(typingSpeedCPM);

        // End custom mode
        ui.showEndCustom();
    }
}