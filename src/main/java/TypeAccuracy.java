import java.util.ArrayList;

public class TypeAccuracy {
    private ArrayList<String> testText;
    private ArrayList<String> userText;

    public TypeAccuracy(ArrayList<String> userText) {
        this.userText = userText;
    }

    public void setTestText(ArrayList<String> testText) {
        this.testText = testText;
    }

    public void updateUserInput(String userInput) {
        userText.add(userInput);
    }

    public double getTypeAccuracy() {
        int testTotalWordCount = 0;
        int userTotalWordCount = 0;
        int correctWordCount = 0;
        int leastWordCount = 0;
        for (int i = 0; i < testText.size(); i++) {
            String[] splitTestWords = testText.get(i).split(" ");
            testTotalWordCount += splitTestWords.length;
            String[] splitUserWords = userText.get(i).split(" ");
            userTotalWordCount += splitUserWords.length;

            if (splitTestWords.length > splitUserWords.length) {
                leastWordCount = splitUserWords.length;
            }
            else {
                leastWordCount = splitTestWords.length;
            }

            for (int j = 0; j < leastWordCount; j++) {
                if (splitTestWords[j].equals(splitUserWords[j])) {
                    correctWordCount++;
                }
            }

        }
        return (double) correctWordCount / (double) testTotalWordCount;
    }

}
