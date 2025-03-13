import java.util.Scanner;

public class WordCounter {
    public static int countWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0; // Handle null or empty input
        }
        String[] words = input.trim().split("\\s+");
        return words.length;
    }
}
