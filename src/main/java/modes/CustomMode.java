package modes;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import storage.InputUserText;
import typing.MarkedText;
import ui.Ui;
import util.WordCounter;

public class CustomMode {

    private final Ui ui;
    private final Scanner scanner;
    private final TypingTimer typingTimer;

    public CustomMode(Ui ui, Scanner scanner) {
        this.ui = ui;
        this.scanner = scanner;
        this.typingTimer = new TypingTimer();
    }

    public void startCustomMode() throws IOException {
        InputUserText inputUserText = new InputUserText(ui);
        List<String> customText = inputUserText.inputText();
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

        assert wordCount >= 0 : "Word count must be non-negative";
        assert characterCount >= 0 : "Character count must be non-negative";
        assert typingSpeedWPM >= 0 : "Typing speed WPM must be non-negative";
        assert typingSpeedCPM >= 0 : "Typing speed CPM must be non-negative";

        ui.showTypingSpeedWPM(typingSpeedWPM);
        ui.showTypingSpeedCPM(typingSpeedCPM);

        // End custom mode
        ui.showEndCustom();

    }
}
