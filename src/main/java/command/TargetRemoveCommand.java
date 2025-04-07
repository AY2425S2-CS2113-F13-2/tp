package command;

import typing.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.TypingTargets;
import typing.TypingAccuracy;
import typing.TypingTarget;
import typing.TypingTargetList;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class TargetRemoveCommand extends Command {

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

        try {
            String targetNo = command.substring(14);
            int targetNoInt = Integer.parseInt(targetNo);
            TypingTarget target = typingTargetList.getTarget(targetNoInt);
            if (targetNoInt < 1 || targetNoInt > typingTargetList.getTargetCount()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            typingTargetList.removeTarget(targetNoInt);
            ui.showTargetRemoved(target.getString());

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.drawLine();
            ui.showErrorMessage(" No/invalid target index entered. Please input a valid integer target index!");
            ui.drawLine();
        }
    }
}
