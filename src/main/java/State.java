import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class State {
    private Storage storage;
    private ArrayList<Double> highScoreList;

    public State (Storage storage) {
        this.storage = storage;
        try {
            highScoreList = storage.readHighScoreList();
        } catch (IOException e) {
            System.err.println("Error reading score from file.");
        }

        assert highScoreList != null : "highScoreList should not be null";
    }

    public Double getHighScore() {
        if (highScoreList.isEmpty()) {
            return 0.0;
        }
        return highScoreList.get(0);
    }

    public ArrayList<Double> getHighScoreList() {
        return highScoreList;
    }

    private void setHighScoreList(ArrayList<Double> newHighScoreList) {
        highScoreList = newHighScoreList;
        assert highScoreList.equals(newHighScoreList) : "highscorelist should be updated";
    }

    public void updateHighScore(Double accuracy, int wpm) throws IOException {
        double newHighScore = accuracy * wpm;
        highScoreList.add(newHighScore);
        highScoreList = new ArrayList<>(new HashSet<>(highScoreList));
        highScoreList.sort((a, b) -> Double.compare(b, a));
        if (highScoreList.size() > 3) {
            highScoreList = new ArrayList<>(highScoreList.subList(0, 3));
        }
        storage.saveScoreList(highScoreList);
    }
}
