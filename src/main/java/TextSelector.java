import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextSelector {
    private static Logger logger = Logger.getLogger("TextSelectorLogger");
    static {
        logger.setLevel(Level.WARNING);
    }

    public static List<String> selectText(String difficultyLevel, int randomNum) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        List<String> validLevels = Arrays.asList("easy", "intermediate", "difficult");

        if (!validLevels.contains(difficultyLevel)) {
            logger.log(Level.SEVERE, "Invalid user input: '" + difficultyLevel +"'");
            throw new InvalidInputException("Please enter a valid difficulty level.");
        }

        String filePath = "/sample_texts/" + difficultyLevel + randomNum + ".txt";
        try  {
            list = fileReader.readFile(filePath);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error reading file: " + filePath);
            throw new FileProcessingException("There has been error when reading file from" + filePath);
        }

        assert list != null;
        return list;
    }

}
