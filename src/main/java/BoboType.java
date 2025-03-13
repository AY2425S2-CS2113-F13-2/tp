import java.util.Scanner;

public class BoboType {

    private Ui ui;
    private Scanner sc;

    public BoboType(String filepath) {
        ui = new Ui();
        sc = new Scanner(System.in);
    }

    public void run() {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);

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
            ui.showStartGame();
            // Placeholder Text and functions, implement other classes here
            String testText = "The quick brown fox jumps over the lazy dog.";
            System.out.println(testText);
            String userInput = sc.nextLine();
            System.out.println("You typed: " + userInput);
            break;

        case "typelist":
            // Add typelist code here
            ui.showTypeList();
            break;

        case "result":
            // Alter to automatically show the result after each game
            ui.showResult();
            break;

        case "typingaccuracy":
            ui.showTypingAccuracy();
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
        BoboType app = new BoboType("./BoboType.java");
        app.run();  // Run the program
    }
}