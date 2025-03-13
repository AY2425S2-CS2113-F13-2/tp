package seedu.duke;

import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        WordCounter wordCounter = new WordCounter();
        FileReader fileReader = new FileReader();
        Scanner scanner = new Scanner(System.in);
        TextSelector textSelector = new TextSelector();
        System.out.println("Enter difficulty level (Choose between easy, intermediate, and difficult)");
        List<String> sample = textSelector.selectText(scanner.nextLine());
        for (String sentence : sample) {
            System.out.println(sentence);
        }

    }
}
