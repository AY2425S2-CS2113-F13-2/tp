import java.util.Scanner;

public class WordCounter {

    public static int countWords() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter words (press Enter on an empty line to finish):");

        int wordCount = 0;

        while (true) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                break; // Exit when the user presses Enter without typing anything
            }

            String[] words = line.split("\\s+"); // Split by spaces or multiple spaces
            wordCount += words.length;
        }

        scanner.close();
        return wordCount;
    }

}
