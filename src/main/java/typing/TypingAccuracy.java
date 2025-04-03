package typing;

import exceptions.BoboTypeException;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculates the accuracy of the user's input against the test text.
 */
public class TypingAccuracy {

    private static final Logger logger = Logger.getLogger("TypeAccuracyLogger");

    static {
        logger.setLevel(Level.WARNING);
    }

    private ArrayList<String> testText;
    private final ArrayList<String> userText;

    /**
     * Constructs a TypeAccuracy object used to find the typing accuracy of the user's input.
     * @param userText ArrayList containing the user's input line by line.
     */
    public TypingAccuracy(ArrayList<String> userText) {
        this.userText = userText;
        logger.log(Level.INFO, "TypeAccuracy object successfully created. UserText initialized.");
    }

    /**
     * Sets the test text to the text randomly selected by the text selector class.
     * @param testText The line by line list of strings corresponding to the test text.
     */
    public void setTestText(ArrayList<String> testText) {
        this.testText = testText;
        logger.log(Level.INFO, "Test text set to: " + testText);
    }

    public void clearUserText() {
        userText.clear();
        logger.log(Level.INFO, "Clearing user text.");
    }

    /**
     * Updates the user input by adding a line to the ArrayList.
     * @param userInput Singular line input by the user.
     */
    public void updateUserInput(String userInput) {
        userText.add(userInput);
        logger.log(Level.INFO, "User input updated to: " + userInput);
    }

    /**
     * Computes the typing accuracy of the user's input against the test text.
     * @return typing accuracy as a decimal (0.0 to 1.0).
     * @throws BoboTypeException if the test text has not been set.
     */
    public double getTypingAccuracy() throws BoboTypeException {
        if (testText == null) {
            throw new BoboTypeException("Please complete a typing test first");
        }
        int testTotalWordCount = 0;
        int correctWordCount = 0;
        int leastWordCount;
        logger.log(Level.INFO, "going to start calculating typing accuracy");
        for (int i = 0; i < testText.size(); i++) {
            String[] splitTestWords = testText.get(i).split(" ");
            testTotalWordCount += splitTestWords.length;
            String[] splitUserWords = userText.get(i).split(" ");
            leastWordCount = Math.min(splitTestWords.length, splitUserWords.length);

            for (int j = 0; j < leastWordCount; j++) {
                if (splitTestWords[j].equals(splitUserWords[j])) {
                    correctWordCount++;
                }
            }

        }
        double typingAccuracy = (double) correctWordCount / (double) testTotalWordCount;
        assert typingAccuracy >= 0.0 : "typingAccuracy must be a positive number";
        assert typingAccuracy <= 1.0 : "typingAccuracy must be less than or equal to 1.0";
        return typingAccuracy;
    }

}
