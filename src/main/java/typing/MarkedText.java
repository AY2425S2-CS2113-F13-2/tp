package typing;

public class MarkedText {

    private final StringBuilder markedText = new StringBuilder();

    public void update(String testLine, String userInput) {
        String ansiRed = "\u001B[31m";
        String ansiGreen = "\u001B[32m";

        String[] splitTestWords = testLine.split(" ");
        String[] splitUserWords = userInput.split(" ");
        for (int word = 0; word < splitTestWords.length; word++) {
            if (word >= splitUserWords.length) {
                markedText.append(ansiRed).append(splitTestWords[word]);
            } else {
                for (int character = 0;
                     character < Math.max(splitTestWords[word].length(), splitUserWords[word].length());
                     character++) {
                    if (character >= splitTestWords[word].length()) {
                        markedText.append(ansiRed).append(splitUserWords[word].charAt(character));
                    } else if (character >= splitUserWords[word].length()) {
                        markedText.append(ansiRed).append(splitTestWords[word].charAt(character));
                    } else {
                        if (splitTestWords[word].charAt(character) == splitUserWords[word].charAt(character)) {
                            markedText.append(ansiGreen).append(splitTestWords[word].charAt(character));
                        } else {
                            markedText.append(ansiRed).append(splitTestWords[word].charAt(character));
                        }
                    }
                }
            }
            markedText.append(" ");
        }
        markedText.append("\n");
    }

    public void print() {
        String ansiReset = "\u001B[0m";
        System.out.println(this. markedText + ansiReset);
    }
}
