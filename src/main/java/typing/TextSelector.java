package typing;

import exceptions.FileProcessingException;
import exceptions.InvalidInputException;
import storage.Milestones;
import util.FileReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TextSelector {
    private static Logger logger = Logger.getLogger("TextSelectorLogger");
    private static final List<String> validLevels = Arrays.asList("easy", "intermediate", "difficult");
    private static final List<String> validLengths = Arrays.asList("short", "medium", "long");

    static {
        logger.setLevel(Level.WARNING);
    }

    public static List<String> selectText(String difficultyLevel, String textLength, int randomNum) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();

        if (!validLevels.contains(difficultyLevel)) {
            throw new InvalidInputException("Please enter a valid difficulty level.");
        }
        if (!validLengths.contains(textLength)) {
            throw new InvalidInputException("Please enter a valid text length.");
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

    /**
     * Selects text based on the default difficulty stored in milestones.txt.
     * @param textLength "short", "medium", or "long"
     * @param randomNum The random number used to select a specific file (e.g., 1.txt, 2.txt)
     * @param milestonePath Path to milestones.txt file (e.g., "data/milestones.txt")
     */
    public static List<String> selectTextDefault(String textLength, int randomNum, String milestonePath) {
        Milestones milestones = new Milestones(milestonePath);
        String defaultDifficulty = milestones.getCurrentDifficulty();
        return selectText(defaultDifficulty, textLength, randomNum);
    }

}
