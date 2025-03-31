package command;

import modes.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import typing.TypingTargetScore;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class TargetscoreaddCommand extends Command {

    @Override
    public void execute(Ui ui, Scanner sc, Milestones milestones, TypingTimer typingTimer, TypingAccuracy typingAccuracy,
                        TypingTargetList typingTargetList, State state, AutoAdjust autoAdjust) throws IOException {
        System.out.println("Please enter a typing speed target you would like to hit (WPM)!");
        try {
            String targetScore = sc.nextLine().trim();
            long targetScoreLong = Long.parseLong(targetScore);
            typingTargetList.addTarget(new TypingTargetScore(targetScoreLong));
            typingTargetList.print();
        } catch (NumberFormatException e) {
            System.out.println("Invalid target score entered. Please provide a valid integer!");
        }
    }
}
