import modes.TimeLimitMode;
import modes.ZenMode;
import storage.AutoAdjust;
import storage.Milestones;
import modes.TypingTimer;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import typing.TextSelector;
import typing.TypingTarget;
import typing.TypingTargetSpeed;
import typing.TypingTargetScore;
import ui.Ui;
import parser.Parser;
import util.RandNumGenerator;
import exceptions.BoboTypeException;
import exceptions.FileProcessingException;
import exceptions.InvalidInputException;
import storage.State;
import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoboType {
    private static final int NUM_OF_TEXTS = 3;

    private final Ui ui;
    private final Scanner sc;
    private final TypingAccuracy typingAccuracy;
    private final TypingTimer typingTimer;
    private final State state;
    private final Milestones milestones;
    private final AutoAdjust autoAdjust;
    private final TypingTargetList typingTargetList;
    private boolean isExit = false;


    public BoboType(String filepath) {
        Storage storage = new Storage(filepath);
        state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        typingAccuracy = new TypingAccuracy(new ArrayList<>());
        typingTimer = new TypingTimer();
        milestones = new Milestones("data/milestones.txt");
        autoAdjust = new AutoAdjust(milestones, ui, state);
        typingTargetList = new TypingTargetList();
    }



    public void run() throws IOException {
        ui.showWelcome();

        while (!isExit) {
            String input = sc.nextLine();
            String[] inputParts = Parser.parseCommand(input);

            String command = inputParts[0];

            if (command.equals("exit")) {
                ui.showExit();
                sc.close();
                isExit = true;
                return;  // Exit the method (and the program)
            } else {
                Parser.handleCommand(inputParts, ui, sc, milestones, typingTimer, typingAccuracy, typingTargetList, state, autoAdjust);  // Handle any other commands
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) throws IOException {
        BoboType app = new BoboType("data/BoboType.txt");
        app.run();  // Run the program
    }
}
