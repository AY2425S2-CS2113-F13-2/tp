import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File file;
    public static ArrayList<Double> data = new ArrayList<>();

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

    public void sortScoreList() {
        data.sort((a, b) -> Double.compare(b, a));
    }

    public void saveScoreList(Double newHighScore) throws IOException {
        readHighScoreList();
        data.add(newHighScore);
        sortScoreList();
        if (data.size() > 3) {
            data = new ArrayList<>(data.subList(0, 3));
        }
        FileWriter save = new FileWriter(file);
        for (Double score : data) {
            save.write(score + "\n");
        }
        save.close();
    }

    public ArrayList<Double> readHighScoreList() throws FileNotFoundException {
        ArrayList<Double> score = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            try {
                score.add(Double.parseDouble(scanner.nextLine()));
            } catch (Exception e) {
                System.err.println("Error reading high score list: " + e.getMessage());
            }
        }
        data = score;
        return score;
    }

//    public void saveScoreToFile(Double newHighScore) throws IOException {
//        FileWriter save = new FileWriter(file);
//        save.write(String.valueOf(newHighScore));
//        save.close();
//    }

    public Double readHighScoreFromFile() throws IOException {
        Scanner scanner = new Scanner(file);
        try {
            if (scanner.hasNextLine()) {
                return Double.parseDouble(scanner.nextLine());
            } else {
                return 0.0; // Default value if no valid double is found
            }
        } catch (Exception e) {
            System.err.println("Error reading high score from file: " + e.getMessage());
            return 0.0;
        }
    }
}
