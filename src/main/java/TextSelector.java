import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextSelector {
    private static int numOfTextsPerLevel = 3;

    private static Logger logger = Logger.getLogger("TextSelectorLogger");
    static {
        logger.setLevel(Level.WARNING);
    }

    public static List<String> selectText(String difficultyLevel, int randomNum) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        List<String> validLevels = Arrays.asList("easy", "intermediate", "difficult");
        assert !validLevels.contains(difficultyLevel): "difficulty level should be easy, intermediate, or difficult.";
        assert randomNum <= numOfTextsPerLevel && randomNum >= 1: "randomNum should be between 1 and " + numOfTextsPerLevel + ".";

        if (!validLevels.contains(difficultyLevel)) {
            logger.log(Level.SEVERE, "Invalid user input: '" + difficultyLevel +"'");
            throw new InvalidInputException("Invalid user input: '" + difficultyLevel +"'");
        }

        try {
            list = fileReader.readFile("./sample_texts/" + difficultyLevel + randomNum + ".txt");
        } catch (FileProcessingException e) {
            logger.log(Level.SEVERE, "Error reading file: ", e.getMessage());
            throw new FileProcessingException("Error reading file: " + e.getMessage());
        }
        return list;
    }

    public static int getRandomTextIndex() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

}
