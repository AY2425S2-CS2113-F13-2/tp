import java.util.Scanner;

/**
 * Typing practice mode where user can type any word they wish and find their typing speed.
 */
public class ZenMode {

    private TypingTimer typingTimer;
    private Scanner sc;
    private int wordCount;
    private int characterCount;
    private Ui ui;
    private int typingSpeedInWPM;
    private int typingSpeedInCPM;
    private double typingDuration;


    /**
     * Constructs a ZenMode object used for the Zen practice mode.
     * @param typingTimer TypingTimer to measure typing time.
     * @param sc Scanner to take in user input.
     * @param ui To display messages to user.
     */
    public ZenMode(TypingTimer typingTimer, Scanner sc, Ui ui) {
        this.typingTimer = typingTimer;
        this.sc = sc;
        this.ui = ui;
    }

    /**
     * Starts and runs the Zen practice mode.
     */
    public void startZenMode() {
        wordCount = 0;
        characterCount = 0;
        boolean isZenMode = true;

        ui.showZenModeInstructions();
        typingTimer.start();
        while (isZenMode) {
            String userInput = sc.nextLine();
            if (userInput.equals("stop_practice")) {
                isZenMode = false;
            }
            wordCount += WordCounter.countWords(userInput);
            characterCount += userInput.length();
        }
        typingTimer.stop();
        typingDuration = typingTimer.getDurationMin();
        typingSpeedInWPM = ((int) (wordCount / typingDuration));
        typingSpeedInCPM = ((int) (characterCount / typingDuration));

        assert (wordCount >= 1) : "word count must be greater than 0";
        assert (characterCount >= 13) : "character count must be greater than 12";
        assert (typingSpeedInWPM >= 0) : "typing speed must be non-negative";
        assert (typingSpeedInCPM >= 0) : "typing speed must be non-negative";

        ui.showZenModeEndGame(wordCount, typingSpeedInWPM, typingSpeedInCPM);
    }

    public int getTypingSpeedInWPM() {
        return typingSpeedInWPM;
    }

    public int getTypingSpeedInCPM() {
        return typingSpeedInCPM;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

}
