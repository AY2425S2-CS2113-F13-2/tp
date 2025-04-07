package command;

import modes.TypingTimer;
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
public class TargetspeedaddCommand extends Command {

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
        System.out.println("Please enter a typing speed target you would like to hit (WPM)!");
        try {
            String targetSpeed = sc.nextLine().trim();
            long targetSpeedLong = Long.parseLong(targetSpeed);
            TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(targetSpeedLong, false);
            typingTargetList.addTarget(typingTargetSpeed);
            typingTargets.update(typingTargetList);
            ui.showTargetAdded(typingTargetSpeed.getString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid target speed entered. Please provide a valid integer!");
        }
    }
}
