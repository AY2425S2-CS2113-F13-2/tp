import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoboType {
    private static final int NUM_OF_TEXTS = 3;

    private final Ui ui;
    private final Scanner sc;
    private int wordCount;
    private int characterCount;
    private final TypeAccuracy typeAccuracy;
    private final Timer timer;
    private final State state;


    public BoboType(String filepath) {
        Storage storage = new Storage(filepath);
        state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        wordCount = 0;
        characterCount = 0;
        typeAccuracy = new TypeAccuracy(new ArrayList<>());
        timer = new Timer();
    }

    public void run() {
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


    private void handleCommand(String[] inputParts) {
        String command = inputParts[0];

        switch (command) {
        case "start":
            List<String> testText;

            while (true) {
                try {
                    ui.chooseDifficulty();
                    String difficultyLevel = sc.nextLine().trim();

                    ui.chooseLength();
                    String textLength = sc.nextLine().trim();

                    int randomNum = RandNumGenerator.randInt(1, NUM_OF_TEXTS);

                    testText = TextSelector.selectText(difficultyLevel, textLength, randomNum);
                    break;
                } catch (InvalidInputException | FileProcessingException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }

            typeAccuracy.setTestText((ArrayList<String>) testText);
            ui.showStartGame();
            wordCount = 0;
            characterCount = 0;

            timer.start();

            for (String s : testText) {
                System.out.println(s);
                String userInput = sc.nextLine();
                typeAccuracy.updateUserInput(userInput);
                wordCount += WordCounter.countWords(userInput);
                characterCount += userInput.length();
            }

            timer.stop();

            ui.showEndGame();
            break;


        case "result":
            // Alter to automatically show the result after each game
            ui.showResult();
            double duration = timer.getDurationMin();
            ui.showTypingSpeedWPM((int) (wordCount / duration));
            ui.showTypingSpeedCPM((int) (characterCount / duration));
            ui.showEndGame();
            break;

        case "typingaccuracy":
            ui.showTypingAccuracy(typeAccuracy.getTypeAccuracy());
            break;

        case "highscore":
            double time = timer.getDurationMin();
            state.updateHighScore(typeAccuracy.getTypeAccuracy(), (int) (wordCount / time));
            ui.showHighScore();
            break;

        default:
            ui.drawLine();
            System.out.println("What does this mean??");
            ui.drawLine();
        }
    }

    // Main method to start the program
    public static void main(String[] args) {
        BoboType app = new BoboType("data/BoboType.txt");
        app.run();  // Run the program
    }
}
