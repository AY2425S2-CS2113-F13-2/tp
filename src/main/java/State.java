import java.io.IOException;
import java.util.ArrayList;

public class State {
    private Double highScore;
    private Storage storage;
    private ArrayList<Double> highScoreList;

    public State (Storage storage) {
        this.storage = storage;
        try {
            highScore = storage.readHighScoreFromFile();
            highScoreList = storage.readHighScoreList();
        } catch (IOException e) {
            highScore = 0.0;
        }

    }

    public Double getHighScore() {
        return highScore;
    }

    public ArrayList<Double> getHighScoreList() {
            return highScoreList;
    }

    private void setHighScore(Double newHighScore) {
        highScore = newHighScore;
        try {
            storage.saveScoreList(newHighScore);
        } catch (IOException e) {
            System.err.println("Error saving high score to file.");
        }
    }

    public void updateHighScore(Double accuracy, int wpm) {
        double newHighScore = accuracy * wpm;
        assert highScore >= 0.0 : "highscore must be a positive number";
        if (newHighScore > highScore) {
            setHighScore(newHighScore);
        }
    }
}
