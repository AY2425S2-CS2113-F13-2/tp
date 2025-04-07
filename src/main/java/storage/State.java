package storage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;

public class State {
    private Storage storage;
    private ArrayList<Double> highScoreList;
    private static final DecimalFormat df = new DecimalFormat("0.00");

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
            return 0.00;
        }
        return Double.parseDouble(df.format(highScoreList.get(0)));
    }

    public ArrayList<Double> getHighScoreList() {
        ArrayList<Double> formattedList = new ArrayList<>();
        for (Double score : highScoreList) {
            formattedList.add(Double.parseDouble(df.format(score)));
        }
        return formattedList;
    }

    private void setHighScoreList(ArrayList<Double> newHighScoreList) {
        highScoreList = newHighScoreList;
        assert highScoreList.equals(newHighScoreList) : "highscorelist should be updated";
    }

    public void updateHighScore(Double accuracy, int wpm) throws IOException {
        double newHighScore = accuracy * wpm;
        newHighScore = Double.parseDouble(df.format(newHighScore));
        highScoreList.add(newHighScore);
        highScoreList = new ArrayList<>(new HashSet<>(highScoreList));
        highScoreList.sort((a, b) -> Double.compare(b, a));
        if (highScoreList.size() > 3) {
            highScoreList = new ArrayList<>(highScoreList.subList(0, 3));
        }
        storage.saveScoreList(highScoreList);
    }
}
