package command;

import typing.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.TypingTargets;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import typing.TypingTargetSpeed;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;


/**
 * Command to handle adding of target speeds.
 */
public class TargetAddSpeedCommand extends Command {


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
            String targetSpeed = command.substring(17);
            long targetSpeedLong = Long.parseLong(targetSpeed);
            if (targetSpeedLong <= 0) {
                throw new NumberFormatException();
            }
            TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(targetSpeedLong, false);
            typingTargetList.addTarget(typingTargetSpeed);
            typingTargets.update(typingTargetList);
            ui.showTargetAdded(typingTargetSpeed.getString());

        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            ui.drawLine();
            ui.showErrorMessage("No/Invalid target speed entered. Please provide a positive valid integer!");
            ui.drawLine();
        }
    }
}
