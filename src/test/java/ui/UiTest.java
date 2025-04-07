package ui;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import storage.State;
import storage.Storage;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiTest {
    private Ui ui;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final String testFilePath = "data/testBoboType.txt";

    // Create a mock class that implements the State interface
    public static class MockState extends State {
        private String value;

        public MockState(Storage storage) {
            super(storage);
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public Double getHighScore() {
            return 0.0; // Provide a default implementation
        }
    }

    @BeforeEach
    void setUp() {
        Storage storage = new Storage(testFilePath);
        State state = new MockState(storage);
        ui = new Ui(state); // Pass MockState as State
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void readInput_returnsUserInput() {
        assertEquals("target list", ui.readInput(new Scanner("target list")));
    }

    @Test
    void showWelcome_printsCorrectOutput() {
        ui.showWelcome();
        String output = outputStream.toString();
        assertTrue(output.contains(" Welcome to BoboType!"));
        assertTrue(output.contains(" Type 'start' to start the game."));
    }

    @Test
    void showExit_printsCorrectOutput() {
        ui.showExit();
        String output = outputStream.toString();
        assertTrue(output.contains(" Bye. Hope to see you again soon!"));
    }

    @Test
    void chooseMode_printsCorrectOutput() {
        ui.chooseMode();
        String output = outputStream.toString();
        assertTrue(output.contains(" Select your mode: "));
        assertTrue(output.contains(" Type: 'normal' or 'timeLimit' or 'zen' or 'custom'"));
    }

    @Test
    void chooseDifficulty_printsCorrectOutput() {
        ui.chooseDifficulty();
        String output = outputStream.toString();
        assertTrue(output.contains(" Select your difficulty: "));
        assertTrue(output.contains(" Type: 'easy' or 'intermediate' or 'difficult'"));
    }

    @Test
    void chooseLength_printsCorrectOutput() {
        ui.chooseLength();
        String output = outputStream.toString();
        assertTrue(output.contains(" Select your text length: "));
        assertTrue(output.contains(" Type: 'short' or 'medium' or 'long'"));
    }

    @Test
    void showErrorMessage_printsCorrectOutput() {
        ui.showErrorMessage("message");
        String output = outputStream.toString();
        assertTrue(output.contains(" *** Oops! *** \n -> message"));
    }

    @Test
    void showResult_printsCorrectOutput() {
        ui.showResult();
        String output = outputStream.toString();
        assertTrue(output.contains(" Hope you enjoyed the round! Here are your stats:"));
    }

    @Test
    void showTypingSpeedWPM_printsCorrectOutput() {
        ui.showTypingSpeedWPM(42);
        String output = outputStream.toString();
        assertTrue(output.contains(" Typing speed (WPM): 42 WPM"));
    }

    @Test
    void showTypingSpeedCPM_printsCorrectOutput() {
        ui.showTypingSpeedCPM(42);
        String output = outputStream.toString();
        assertTrue(output.contains(" Typing speed (CPM): 42 CPM"));
    }

    @Test
    void showTypingScore_printsCorrectOutput() {
        ui.showTypingScore(42.0001);
        String output = outputStream.toString();
        assertTrue(output.contains(" Typing score (Effective WPM): 42.00 WPM"));
    }

    @Test
    void showTypingAccuracy_printsCorrectOutput() {
        ui.showTypingAccuracy(0.99876);
        String output = outputStream.toString();
        assertTrue(output.contains(" Typing accuracy: 99.88%"));
    }

    @Test
    void showHighScore_printsCorrectOutput() {
        ui.showHighScore();
        String output = outputStream.toString();
        assertTrue(output.contains(" Your high score is: 0.0"));
    }

    @Test
    void showHighScoreList_printsCorrectOutput() {
        ui.showHighScoreList();
        String output = outputStream.toString();
        assertTrue(output.contains(" Top 3 High Scores: "));
    }

    @Test
    void showTargetAdded_printsCorrectOutput() {
        ui.showTargetAdded(" Target Score: 50 | Not Achieved");
        String output = outputStream.toString();
        assertTrue(output.contains(" Target added!"));
        assertTrue(output.contains(" Target Score: 50 | Not Achieved"));
    }

    @Test
    void showTargetRemoved_printsCorrectOutput() {
        ui.showTargetRemoved(" Target Score: 50 | Not Achieved");
        String output = outputStream.toString();
        assertTrue(output.contains(" Target removed!"));
        assertTrue(output.contains(" Target Score: 50 | Not Achieved"));
    }

    @Test
    void showEndGame_printsCorrectOutput() {
        ui.showEndGame();
        String output = outputStream.toString();
        assertTrue(output.contains(" You finished the practice! Please type"));
        assertTrue(output.contains(" - 'typingaccuracy' to view your typing accuracy"));
        assertTrue(output.contains("    of your previous typing test in normal mode"));
        assertTrue(output.contains(" - 'highscore' to view your high score"));
        assertTrue(output.contains(" - 'highscore list' to view your top 3 high scores"));
        assertTrue(output.contains(" - 'milestone' to view your default difficulty level"));
        assertTrue(output.contains(" - 'target list' to view your typing targets"));
        assertTrue(output.contains(" - 'target add speed SPEED' to add a typing speed target"));
        assertTrue(output.contains(" - 'target add score SCORE' to add a typing score target"));
        assertTrue(output.contains(" - 'target remove TARGET_INDEX' to remove a typing target"));
        assertTrue(output.contains(" - 'progress' to view your typing progress"));
        assertTrue(output.contains(" - 'exit' to exit or"));
        assertTrue(output.contains(" - 'start' to start the new practice."));
    }

    @Test
    void showZenModeInstructions_printsCorrectOutput() {
        ui.showZenModeInstructions();
        String output = outputStream.toString();
        assertTrue(output.contains(" Welcome to Zen Mode, you can type out anything to your"));
        assertTrue(output.contains(" heart's content and find out your typing speed."));
        assertTrue(output.contains(" Typing 'start' on a new line will start the typingTimer and typing the command"));
        assertTrue(output.contains(" 'stop_practice' on a new line will stop the practice."));
    }

    @Test
    void showZenModeStartPrompt_printsCorrectOutput() {
        ui.showZenModeStartPrompt();
        String output = outputStream.toString();
        assertTrue(output.contains(" Please type 'start' on a new line to start Zen mode"));
    }

    @Test
    void showTimeLimitModeInstructions_printsCorrectOutput() {
        ui.showTimeLimitModeInstructions(ui);
        String output = outputStream.toString();
        assertTrue(output.contains(" Welcome to timeLimit mode."));
        assertTrue(
                output.contains(" *** In this mode, you can view your input ONLY after you finish your sentence ***")
        );
        assertTrue(output.contains(" *** i.e when ENTER is pressed!!! ***"));
        assertTrue(output.contains(" Are you ready? The game will begin in..."));
        assertTrue(output.contains(" 3"));
        assertTrue(output.contains(" 2"));
        assertTrue(output.contains(" 1"));
        assertTrue(output.contains(" Start!"));
    }

    @Test
    void showTimeLimitMiddleMessage_printsCorrectOutput() {
        ui.showTimeLimitMiddleMessage();
        String output = outputStream.toString();
        assertTrue(output.contains(" *** Press enter to continue... "));
        assertTrue(output.contains("Previous input (shown below, if any) will be cleared. ***"));
    }

    @Test
    void showTimeLimitResult_printsCorrectOutput() {
        ui.showTimeLimitResult(3, 2);
        String output = outputStream.toString();
        assertTrue(output.contains(" You finished the timeLimit Mode Practice!"));
        assertTrue(output.contains(" - Num of correct lines: 2 lines out of 3 lines"));
        assertTrue(output.contains(" *** Please press enter to continue. ***"));
    }

    @Test
    void showMilestoneAchieved_printsCorrectOutput() {
        ui.showMilestoneAchieved("intermediate", 80);
        String output = outputStream.toString();
        assertTrue(output.contains(" Congrats! You hit the milestone:"));
        assertTrue(output.contains(" -> Achieved a score of 80 in intermediate mode!"));
        assertTrue(output.contains(" You've been promoted to a new difficulty level"));
    }

    @Test
    void showCurrentMilestone_printsCorrectOutput() {
        ui.showCurrentMilestone("intermediate");
        String output = outputStream.toString();
        assertTrue(output.contains(" Current milestone: intermediate"));
    }

    @Test
    void showString_printsCorrectOutput() {
        ui.showString("This is a sentence.");
        String output = outputStream.toString();
        assertTrue(output.contains("This is a sentence."));
    }
}
