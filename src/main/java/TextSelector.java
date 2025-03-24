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

    public static List<String> selectText(String difficultyLevel, String textLength, int randomNum) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        List<String> validLevels = Arrays.asList("easy", "intermediate", "difficult", "zen");
        List<String> validLengths = Arrays.asList("short", "medium", "long");

        if (!validLevels.contains(difficultyLevel)) {
            throw new InvalidInputException("Please enter a valid difficulty level.");
        }
        if (!validLengths.contains(textLength)) {
            throw new InvalidInputException("Please enter a valid text length.");
        }

        if (difficultyLevel.equals("zen")) {
            list.add("IN_ZEN_MODE");
            return list;
        }

        String filePath = "/sample_texts/" + difficultyLevel + "/" + textLength + "/" + randomNum + ".txt";
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
