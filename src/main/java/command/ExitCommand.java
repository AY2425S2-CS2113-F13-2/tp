package command;

//@@author ravi-viswa105

import modes.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.TypingTargets;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

/**
 * Command for handling exiting of application.
 */
public class ExitCommand extends Command {

    @Override
    public void execute(
            Ui ui,
            Scanner sc,
            Milestones milestones,
            TypingTimer typingTimer,
            TypingAccuracy typingAccuracy,
            TypingTargetList typingTargetList,
            TypingTargets typingTargets,
            State state,
            AutoAdjust autoAdjust,
            String command
    ) throws IOException {
        ui.showExit();
    }

    /**
     * Sets isExit to true
     *
     * @return Always returns true to ensure application exits
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
