import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoboType {
    private static final int NUM_OF_TEXTS = 3;

    private final Ui ui;
    private final Scanner sc;
    private int wordCount;
    private int characterCount;
    private final TypingAccuracy typingAccuracy;
    private final TypingTimer typingTimer;
    private final State state;
    private final TypingTargetList typingTargetList;

    public BoboType(String filepath) {
        Storage storage = new Storage(filepath);
        state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        wordCount = 0;
        characterCount = 0;
        typingAccuracy = new TypingAccuracy(new ArrayList<>());
        typingTimer = new TypingTimer();
        typingTargetList = new TypingTargetList();
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
                // select difficulty and length of the test
                List<String> testText;
                String difficultyLevel;
                String textLength;
                while (true) {
                    try {
                        ui.chooseDifficulty();
                        difficultyLevel = sc.nextLine().trim();

                        ui.chooseLength();
                        textLength = sc.nextLine().trim();

                        int randomNum = RandNumGenerator.randInt(1, NUM_OF_TEXTS);

                        testText = TextSelector.selectText(difficultyLevel, textLength, randomNum);
                        break;
                    } catch (InvalidInputException | FileProcessingException e) {
                        ui.showErrorMessage(e.getMessage());
                    }
                }
                // time limit mode
                if (mode.equals("timeLimit")) {
                    int timeLimit;
                    int numOfLines;
                    int numOfCorrect;
                    TimeLimitMode timeLimitMode = new TimeLimitMode();
                    if (difficultyLevel.equals("easy")) {
                        timeLimit = 10;
                    } else if (difficultyLevel.equals("intermediate")) {
                        timeLimit = 15;
                    } else {
                        timeLimit = 20;
                    }
                    ui.showTimeLimitModeInstructions(timeLimit);

                    try {
                        timeLimitMode.run(testText, timeLimit, sc);
                    } catch (InterruptedException e) {
                        ui.showErrorMessage(e.getMessage());
                    }
                    numOfLines = testText.size();
                    numOfCorrect = timeLimitMode.getNumOfCorrect();
                    ui.showTimeLimitResult(numOfLines, numOfCorrect);
                    sc.nextLine(); // to clear the input
                } else { // normal mode
                    typingAccuracy.setTestText((ArrayList<String>) testText);
                    ui.showStartGame();
                    wordCount = 0;
                    characterCount = 0;

                    typingTimer.start();

                    for (String s : testText) {
                        System.out.println(s);
                        String userInput = sc.nextLine();
                        typingAccuracy.updateUserInput(userInput);
                        wordCount += WordCounter.countWords(userInput);
                        characterCount += userInput.length();
                    }
                    typingTimer.stop();

                    ui.showResult();
                    double duration = typingTimer.getDurationMin();
                    int typingSpeedWPM = (int) (wordCount / duration);
                    int typingSpeedCPM = (int) (characterCount / duration);
                    double typingAccuracyDouble = typingAccuracy.getTypingAccuracy();
                    double typingScore = (double) typingSpeedWPM * typingAccuracyDouble;
                    ui.showTypingSpeedWPM(typingSpeedWPM);
                    ui.showTypingSpeedCPM(typingSpeedCPM);
                    // ui.showTypingAccuracy(typingAccuracyDouble);
                    ui.showTypingScore(typingScore);

                    for (TypingTarget typingTarget : typingTargetList.getTypingTargetList()) {
                        if (typingTarget instanceof TypingTargetSpeed) {
                            if (typingSpeedWPM >= typingTarget.getTarget()) {
                                typingTarget.setHit(true);
                            }
                            typingTarget.printHit();
                        } else if (typingTarget instanceof TypingTargetScore) {
                            if (typingScore >= typingTarget.getTarget()) {
                                typingTarget.setHit(true);
                            }
                            typingTarget.printHit();
                        }
                    }
                }
                double time = typingTimer.getDurationMin();
                state.updateHighScore(typingAccuracy.getTypingAccuracy(), (int) (wordCount / time));
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
