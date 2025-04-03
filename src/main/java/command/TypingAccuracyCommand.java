package command;

//@@author ravi-viswa105

import exceptions.BoboTypeException;
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
 * Handles output of typingaccuracy.
 */
public class TypingAccuracyCommand extends Command {

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
            AutoAdjust autoAdjust
    ) throws IOException {
        try {
            ui.showTypingAccuracy(typingAccuracy.getTypingAccuracy());
        } catch (BoboTypeException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }
}
