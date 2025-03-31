/**
 * Handles automatic adjustment of the user's typing difficulty
 * based on their current performance (WPM).
 */
public class AutoAdjust {
    private final Milestones milestones;
    private final Ui ui;

    /**
     * Constructs an AutoAdjust instance.
     *
     * @param milestones The milestones tracker used to store and update user progress.
     * @param ui         The UI component used to display milestone achievement messages.
     */
    public AutoAdjust(Milestones milestones, Ui ui) {
        this.milestones = milestones;
        this.ui = ui;
    }

    /**
     * Evaluates the user's current WPM performance.
     * If the user has achieved the WPM goal for the current difficulty,
     * updates their milestone and promotes them to the next level.
     *
     * @param wpm Words per minute achieved in the latest typing session.
     */
    public void evaluate(int wpm) {
        String difficulty = milestones.getCurrentDifficulty();
        if (milestones.checkAndUpdate(difficulty, wpm)) {
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
