import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TextSelector {
    public static List<String> selectText(String difficultyLevel, int randomNum) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        List<String> validLevels = Arrays.asList("easy", "intermediate", "difficult");

        if (!validLevels.contains(difficultyLevel)) {
            throw new InvalidInputException("Invalid user input: '" + difficultyLevel +"'");
        }

        try {
            list = fileReader.readFile("./sample_texts/" + difficultyLevel + randomNum + ".txt");
        } catch (FileProcessingException e) {
            throw new FileProcessingException("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static int getRandomTextIndex() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

}
