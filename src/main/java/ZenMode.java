import java.util.Scanner;

/**
 * Typing practice mode where user can type any word they wish and find their typing speed
 */
public class ZenMode {

    private Timer timer;
    private Scanner sc;
    private int wordCount;
    private int characterCount;
    private Ui ui;

    /**
     * Constructs a ZenMode object used for the Zen practice mode
     * @param timer Timer to measure typing time
     * @param sc Scanner to take in user input
     * @param ui To display messages to user
     */
    public ZenMode(Timer timer,Scanner sc, Ui ui) {
        this.timer = timer;
        this.sc = sc;
        this.ui = ui;
    }

    /**
     * Starts and runs the Zen practice mode
     */
    public void startZenMode() {
        wordCount = 0;
        characterCount = 0;
        boolean isZenMode = true;

        ui.showZenModeInstructions();
        timer.start();
        while (isZenMode) {
            String userInput = sc.nextLine();
            if (userInput.equals("stop_practice")) {
                isZenMode = false;
            }
            wordCount += WordCounter.countWords(userInput);
            characterCount += userInput.length();
        }
        timer.stop();
        double duration = timer.getDurationMin();
        int typingSpeedInWPM = ((int) (wordCount / duration));
        int typingSpeedInCPM = ((int) (characterCount / duration));

        assert (wordCount >= 0) : "word count must be non-negative";
        assert (characterCount >= 0) : "character count must be non-negative";
        assert (typingSpeedInWPM >= 0) : "typing speed must be non-negative";
        assert (typingSpeedInCPM >= 0) : "typing speed must be non-negative";

        ui.showZenModeEndGame(wordCount, typingSpeedInWPM, typingSpeedInCPM);
    }

}
