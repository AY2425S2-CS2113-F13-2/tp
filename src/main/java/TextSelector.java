import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TextSelector {
    public static List<String> selectText(String difficultyLevel) {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader();
        Random random = new Random();

        int randomNum = random.nextInt(3) + 1;
        list = fileReader.readFile("./sample_texts/" + difficultyLevel + randomNum + ".txt");
        return list;
    }

}
