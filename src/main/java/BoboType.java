import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoboType {
    private final Ui ui;
    private final Scanner sc;
    private int wordCount;
    private int characterCount;
    private final TypingAccuracy typingAccuracy;
    private final TypingTimer typingTimer;
    private final State state;
    private final Milestones milestones;
    private final AutoAdjust autoAdjust;
    private final TypingTargetList typingTargetList;
    private List<String> testText;
    private final TextSelector textSelector;

    public BoboType(String filepath) {
        Storage storage = new Storage(filepath);
        state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        wordCount = 0;
        characterCount = 0;
        typingAccuracy = new TypingAccuracy(new ArrayList<>());
        typingTimer = new TypingTimer();
        milestones = new Milestones("data/milestones.txt");
        autoAdjust = new AutoAdjust(milestones, ui);
        typingTargetList = new TypingTargetList();
        textSelector = new TextSelector(sc, ui);
    }

    private void handleCommand(String[] inputParts) throws IOException {
        String command = inputParts[0];

        switch (command) {
        case "start":
            ui.chooseMode();
            String mode = sc.nextLine().trim();
            if (mode.equals("zen")) {
                ZenMode zenMode = new ZenMode(typingTimer, sc, ui);
                zenMode.startZenMode();
            } else {
                testText = textSelector.selectText();
                // time limit mode
                if (mode.equals("timeLimit")) {
                    TimeLimitMode timeLimitMode = new TimeLimitMode(ui, sc);
                    try {
                        timeLimitMode.StartTimeLimitMode(testText, textSelector.getDifficultyLevel());
                    } catch (InterruptedException e) {
                        ui.showErrorMessage(e.getMessage());
                    }
                } else { // normal mode
                    NormalMode normalMode = new NormalMode(ui, sc, typingTargetList, state, autoAdjust, typingAccuracy);
                    normalMode.startNormalMode(testText);
                }

                ui.showEndGame();
            }
            break;

        case "typingaccuracy":
            try {
                ui.showTypingAccuracy(typingAccuracy.getTypingAccuracy());
            } catch (BoboTypeException e) {
                ui.showErrorMessage(e.getMessage());
            }
            break;

        case "highscore":
            ui.showHighScore();
            break;

        case "highscorelist":
            ui.showHighScoreList();
            break;

        case "milestone":
            String difficulty = milestones.getCurrentDifficulty();
            ui.showCurrentMilestone(difficulty);
            break;

        case "targetspeedadd":
            System.out.println("Please enter a typing speed target you would like to hit (WPM)!");
            try {
                String targetSpeed = sc.nextLine().trim();
                long targetSpeedLong = Long.parseLong(targetSpeed);
                typingTargetList.addTarget(new TypingTargetSpeed(targetSpeedLong));
                typingTargetList.print();
            } catch (NumberFormatException e) {
                System.out.println("Invalid target speed entered. Please provide a valid integer!");
            }
            break;

        case "targetscoreadd":
            System.out.println("Please enter a typing score target you would like to hit (effective WPM)!");
            try {
                String targetScore = sc.nextLine().trim();
                long targetScoreLong = Long.parseLong(targetScore);
                typingTargetList.addTarget(new TypingTargetScore(targetScoreLong));
                typingTargetList.print();
            } catch (NumberFormatException e) {
                System.out.println("Invalid target score entered. Please provide a valid integer!");
            }
            break;

        default:
            ui.drawLine();
            System.out.println("Invalid command entered. Please provide a valid input!");
            ui.drawLine();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();

        while (true) {
            String input = sc.nextLine();
            String[] inputParts = Parser.parseCommand(input);
            String command = inputParts[0];

            if (command.equals("exit")) {
                ui.showExit();
                sc.close();
                return;  // Exit the method (and the program)
            } else {
                handleCommand(inputParts);  // Handle any other commands
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) throws IOException {
        BoboType app = new BoboType("data/BoboType.txt");
        app.run();  // Run the program
    }
}
