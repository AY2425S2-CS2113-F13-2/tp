import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Double> data = new ArrayList<>();
    File file;

    public Storage (String filePath) {
        this.file = new File(filePath);
        if (!file.exists()) {
            if (file.getParentFile() != null) {
                file.getParentFile().mkdirs();
            }
            try {
                file.createNewFile();
                // populate file with default data;
            } catch (IOException e) {
                System.err.println("Error creating new file: " + filePath);
            }
        }
    }

    public void saveScoreList(ArrayList<Double> newHighScoreList) throws IOException {
        FileWriter save = new FileWriter(file);
        for (Double score : newHighScoreList) {
            save.write(score + "\n");
        }
        save.close();
    }

    public ArrayList<Double> readHighScoreList() throws FileNotFoundException {
        ArrayList<Double> score = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            try {
                String read = scanner.nextLine();
                if (read.isEmpty()) {
                    break;
                }
                score.add(Double.parseDouble(read));
            } catch (Exception e) {
                System.err.println("Error reading high score list: " + e.getMessage());
            }
        }
        data = score;
        assert data != null : "data should not be null";
        assert data.size() <= 3 : "data should not have more than 3 elements";
        return score;
    }
}
