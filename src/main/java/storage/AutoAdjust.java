package storage;

import ui.Ui;

/**
 * Handles automatic adjustment of the user's typing difficulty
 * based on their current performance (WPM).
 */
public class AutoAdjust {
    private final Milestones milestones;
    private final Ui ui;
    private final State state;

    /**
     * Constructs an AutoAdjust instance.
     *
     * @param milestones The milestones tracker used to store and update user progress.
     * @param ui         The UI component used to display milestone achievement messages.
     */
    public AutoAdjust(Milestones milestones, Ui ui, State state) {
        this.milestones = milestones;
        this.ui = ui;
        this.state = state;
    }

    /**
     * Evaluates the user's current highscore performance.
     * If the user has achieved the highscore goal for the current difficulty,
     * updates their milestone and promotes them to the next level
     */
    public void evaluate() {
        String difficulty = milestones.getCurrentDifficulty();
        double latestHighScore = state.getHighScore();
        if (milestones.checkAndUpdate(difficulty, latestHighScore)) {
            int goal;
            switch (difficulty) {
            case "easy":
                goal = 60;
                break;
            case "intermediate":
                goal = 80;
                break;
            case "difficult":
                goal = 100;
                break;
            default:
                goal = 9999;
            }
            ui.showMilestoneAchieved(difficulty, goal);
        }
    }

    public String getDefaultDifficulty() {
        return milestones.getCurrentDifficulty();
    }
}
