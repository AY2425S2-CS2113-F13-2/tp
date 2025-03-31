package command;

import modes.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import typing.TypingTargetSpeed;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class TargetspeedaddCommand extends Command {

    @Override
    public void execute(Ui ui, Scanner sc, Milestones milestones, TypingTimer typingTimer, TypingAccuracy typingAccuracy,
                        TypingTargetList typingTargetList, State state, AutoAdjust autoAdjust) throws IOException {
        System.out.println("Please enter a typing speed target you would like to hit (WPM)!");
        try {
            String targetSpeed = sc.nextLine().trim();
            long targetSpeedLong = Long.parseLong(targetSpeed);
            typingTargetList.addTarget(new TypingTargetSpeed(targetSpeedLong));
            typingTargetList.print();
        } catch (NumberFormatException e) {
            System.out.println("Invalid target speed entered. Please provide a valid integer!");
        }
    }
}
