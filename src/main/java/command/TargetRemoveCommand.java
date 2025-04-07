package command;

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

public class TargetremoveCommand extends Command {

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
        typingTargetList.print();
        System.out.println(" Please enter the index of the target you would like to remove!");
        ui.drawLine();

        try {
            String targetNo = sc.nextLine().trim();
            int targetNoInt = Integer.parseInt(targetNo);
            if (targetNoInt > typingTargetList.getTargetCount()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            typingTargetList.removeTarget(targetNoInt);
            ui.drawLine();
            System.out.println(" Target removed!");
            ui.drawLine();

        } catch (NumberFormatException | IndexOutOfBoundsException e) { // Invalid target index inputted
            ui.drawLine();
            System.out.println(" Invalid target index entered. Please input a valid integer target index!");
            ui.drawLine();
        }
    }
}
