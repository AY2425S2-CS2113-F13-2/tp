import java.io.IOException;

public class State {
    private Double highScore;
    private Storage storage;

    public State (Storage storage) {
        this.storage = storage;
        try {
            highScore = storage.readScoreFromFile();
        } catch (IOException e) {
            highScore = 0.0;
        }

    }

    public Double getHighScore() {
        return highScore;
    }

    private void setHighScore(Double newHighScore) {
        highScore = newHighScore;
        try {
            storage.saveScoreToFile(newHighScore);
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
