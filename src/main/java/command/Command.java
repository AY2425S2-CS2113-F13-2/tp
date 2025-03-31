package command;

import modes.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Abstract class for commands
 * Tracks exit status to check if application should be exited
 */
public class Command {

    /**
     * Computes the typing accuracy and prints to user.
     * @param ui The Ui object used to display output to the users.
     * @param sc The Scanner object used to read user input.
     * @param milestones The Milestone object used to track user milestones.
     * @param typingTimer The TypingTimer object used to track duration of typing.
     * @param typingAccuracy The TypingAccuracy object used to track typing accuracy.
     * @param typingTargetList The TypingTargetList object used to list TypingTarget objects .
     * @param state State for storage.
     * @param autoAdjust The AutoAdjust object for setting difficulty based on user.
     * @throws IOException if storage fails.
     */
    public void execute(Ui ui, Scanner sc, Milestones milestones, TypingTimer typingTimer, TypingAccuracy typingAccuracy,
                        TypingTargetList typingTargetList, State state, AutoAdjust autoAdjust) throws IOException {}

    /**
     * Sets isExit to false by default to continue loop
     * @return false if Command is not ExitCommand (Overridden in ExitCommand)
     */
    public boolean isExit() {
        return false;
    }


}
