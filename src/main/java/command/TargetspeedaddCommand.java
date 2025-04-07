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
        ui.drawLine();
        System.out.println(" Please enter a typing speed target you would like to hit (WPM)!");
        ui.drawLine();

        try {
            String targetSpeed = sc.nextLine().trim();
            long targetSpeedLong = Long.parseLong(targetSpeed);
            if (targetSpeedLong <= 0) {
                throw new NumberFormatException();
            }
            TypingTargetSpeed typingTargetSpeed = new TypingTargetSpeed(targetSpeedLong, false);
            typingTargetList.addTarget(typingTargetSpeed);
            typingTargets.update(typingTargetList);
            ui.showTargetAdded(typingTargetSpeed.getString());
        } catch (NumberFormatException e) {
            ui.drawLine();
            System.out.println(" Invalid target speed entered. Please provide a positive valid integer!");
            ui.drawLine();
        }
    }
}
