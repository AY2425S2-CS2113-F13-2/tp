import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoboType {

    private Ui ui;
    private Scanner sc;
    private TextSelector textSelector;
    private WordCounter wordCounter;
    private int wordCount;
    private TypeAccuracy typeAccuracy;
    private Timer timer;
    private State state;


    public BoboType(String filepath) {
        Storage storage = new Storage(filepath);
        state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        textSelector = new TextSelector();
        wordCounter = new WordCounter();
        wordCount = 0;
        typeAccuracy = new TypeAccuracy(new ArrayList<>());
        timer = new Timer();
    }

    public void run() {
        ui.showWelcome();

        while (true) {
            String input = sc.nextLine();
            String[] inputParts = Parser.parseCommand(input);
            String command = inputParts[0];

            switch (command) {
            case "exit":
                ui.showExit();
                sc.close();
                return;  // Exit the method (and the program)

            default:
                handleCommand(inputParts);  // Handle any other commands
                break;
            }
        }
    }


    private void handleCommand(String[] inputParts) {
        String command = inputParts[0];

        switch (command) {
        case "start":
            List<String> testText = new ArrayList<>();

            while (true) {
                try {
                    ui.chooseDifficulty();
                    int randomNum = textSelector.getRandomTextIndex();
                    testText = textSelector.selectText(sc.nextLine(), randomNum);
                    break;
                } catch (InvalidInputException e) {
                    ui.showErrorMessage(e.getMessage());
                } catch (FileProcessingException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }

            typeAccuracy.setTestText((ArrayList<String>) testText);
            ui.showStartGame();
            wordCount = 0;

            timer.start();

            for (String s : testText) {
                System.out.println(s);
                String userInput = sc.nextLine();
                typeAccuracy.updateUserInput(userInput);
                wordCount += wordCounter.countWords(userInput);
            }

            timer.stop();

            ui.showEndGame();
            break;


        case "result":
            // Alter to automatically show the result after each game
            ui.showResult();
            System.out.println("Typing speed: " + (double) wordCount / timer.getDuration() * 1000 * 60 + " WPM");
            ui.showEndGame();
            break;

        case "typingaccuracy":
            ui.showTypingAccuracy(typeAccuracy.getTypeAccuracy());
            break;

        case "highscore":
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
