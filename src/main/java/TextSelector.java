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

        if (!validLevels.contains(difficultyLevel)) {
            logger.log(Level.SEVERE, "Invalid user input: '" + difficultyLevel +"'");
            throw new InvalidInputException("Invalid user input: '" + difficultyLevel +"'");
        }
        list = SampleTextReader.readSampleText(difficultyLevel, randomNum);
        assert list != null;
        return list;
    }

    public static int getRandomTextIndex() {
        Random random = new Random();
        //TODO: random file chosen in fileReader instead?
        // TODO: bound should be 2 instead? (0, 1, 2) + 1
        int randomNum = random.nextInt(3) + 1;
        assert randomNum <= numOfTextsPerLevel && randomNum >= 1:
            "randomNum is between 1 and " + numOfTextsPerLevel;
        return randomNum;
    }

}
