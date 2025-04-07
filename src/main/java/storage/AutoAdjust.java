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
     * Only promotes to the next difficulty if the score was achieved
     * at the current milestone difficulty.
     *
     * @param playedDifficulty the difficulty level the score was achieved in
     * @param score the score achieved in that round
     */
    public void evaluate(String playedDifficulty, double score) {
        String currentDifficulty = milestones.getCurrentDifficulty();

        if (!playedDifficulty.equals(currentDifficulty)) {
            return;
        }

        if (milestones.checkAndUpdate(currentDifficulty, score)) {
            int goal;
            switch (currentDifficulty) {
            case "easy":
                goal = 60;
                ui.showMilestoneAchieved(currentDifficulty, goal);
                break;
            case "intermediate":
                goal = 80;
                ui.showMilestoneAchieved(currentDifficulty, goal);
                break;
            case "difficult":
                goal = 100;
                ui.showFinalMilestoneAchieved(goal);
                break;
            default:
                goal = 9999;
            }
        }
    }

    public String getDefaultDifficulty() {
        return milestones.getCurrentDifficulty();
    }
}
