import java.io.IOException;

public class State {
    private Double highScore;
    private Storage storage;

    public State () {
        storage = new Storage("data/BoboType.txt");
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

    public void updateHighScore(Double newHighScore) {
        if (newHighScore > highScore) {
            setHighScore(newHighScore);
        }
    }
}
