import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypeAccuracy {

    private static Logger logger = Logger.getLogger("TypeAccuracyLogger");

    static {
        logger.setLevel(Level.WARNING);
    }

    private ArrayList<String> testText;
    private ArrayList<String> userText;

    public TypeAccuracy(ArrayList<String> userText) {
        this.userText = userText;
        logger.log(Level.INFO, "TypeAccuracy object successfully created. UserText initialized.");
    }

    public void setTestText(ArrayList<String> testText) {
        this.testText = testText;
        logger.log(Level.INFO, "Test text set to: " + testText);
    }

    public void updateUserInput(String userInput) {
        userText.add(userInput);
        logger.log(Level.INFO, "User input updated to: " + userInput);
    }

    public double getTypeAccuracy() {
        if (testText == null) {
            logger.log(Level.SEVERE, "Test text is null");
            throw new IllegalStateException("Test text is null");
        }
        int testTotalWordCount = 0;
        int correctWordCount = 0;
        int leastWordCount = 0;
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
        double typeAccuracy = (double) correctWordCount / (double) testTotalWordCount;
        assert typeAccuracy >= 0.0 : "typeAccuracy must be a positive number";
        assert typeAccuracy <= 1.0 : "typeAccuracy must be less than or equal to 1.0";
        return typeAccuracy;
    }

}
