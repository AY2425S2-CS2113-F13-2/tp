package command;

import modes.TypingTimer;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.TypingTargets;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import typing.TypingTargetScore;
import ui.Ui;

import java.io.IOException;
import java.util.Scanner;

public class TargetscoreaddCommand extends Command {

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
            String targetScore = sc.nextLine().trim();
            long targetScoreLong = Long.parseLong(targetScore);
            TypingTargetScore typingTargetScore = new TypingTargetScore(targetScoreLong, false);
            typingTargetList.addTarget(typingTargetScore);
            typingTargets.update(typingTargetList);
            ui.showTargetAdded(typingTargetScore.getString());
        } catch (NumberFormatException e) {
            System.out.println("Invalid target score entered. Please provide a valid integer!");
        }
    }
}
