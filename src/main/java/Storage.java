import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    File file;

    public Storage (String filePath) {
        File file = new File(filePath);
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

    public void saveScoreToFile(Double newHighScore) throws IOException {
        FileWriter save = new FileWriter(file);
        save.write(String.valueOf(newHighScore));
        save.close();
    }

    public Double readScoreFromFile() throws IOException {
        return Double.parseDouble(file.toString());
    }
}
