import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextSelector {
    public static List<String> selectText(String difficultyLevel, int randomNum) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        list = fileReader.readFile("./sample_texts/" + difficultyLevel + randomNum + ".txt");
        return list;
    }

    public static int getRandomTextIndex() {
        Random random = new Random();
        return random.nextInt(3) + 1;
    }

}
