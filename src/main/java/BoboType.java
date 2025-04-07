import command.Command;
import exceptions.InvalidInputException;
import storage.AutoAdjust;
import storage.Milestones;
import storage.TypingTargets;
import storage.State;
import storage.Storage;
import storage.ProgressReport;
import typing.TypingTimer;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import ui.Ui;
import parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import util.TextSelector;

public class BoboType {

    private final Ui ui;
    private final Scanner sc;
    private TypingAccuracy typingAccuracy;
    private final TypingTimer typingTimer;
    private final State state;
    private final Milestones milestones;
    private final AutoAdjust autoAdjust;
    private final TypingTargetList typingTargetList;
    private final TypingTargets typingTargets;
    private final TextSelector textSelector;
    private final ProgressReport progressReport;


    public BoboType(String filepath) throws IOException {
        Storage storage = new Storage(filepath);
        state = new State(storage);
        ui = new Ui(state);
        sc = new Scanner(System.in);
        typingAccuracy = new TypingAccuracy(new ArrayList<>());
        typingTimer = new TypingTimer();
        milestones = new Milestones("data/milestones.txt");
        autoAdjust = new AutoAdjust(milestones, ui, state);
        typingTargetList = new TypingTargetList();
        typingTargets = new TypingTargets("data/typingtargets.txt");
        typingTargets.load(typingTargetList);
        textSelector = new TextSelector(sc, ui);
        progressReport = new ProgressReport("data/progress.txt", ui);

    }


    public void run() throws IOException {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput(sc);
                //Parser userInput = new Parser(input);
                Command c = Parser.parseToCommand(input);
                c.execute(
                        ui, sc, milestones, typingTimer, typingAccuracy,
                        typingTargetList, typingTargets, state, autoAdjust, input
                );
                isExit = c.isExit();
            } catch (InvalidInputException e) {
                ui.showErrorMessage("Invalid command entered. Please provide a valid input!");
            }
        }
    }

    // Main method to start the program
    public static void main(String[] args) throws IOException {
        BoboType app = new BoboType("data/BoboType.txt");
        app.run();  // Run the program
    }
}
