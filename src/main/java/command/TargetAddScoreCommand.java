package command;

import typing.TypingTimer;
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


/**
 * Command to handle adding of Target scores.
 */
public class TargetAddScoreCommand extends Command {

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
            String targetScore = command.substring(17);
            long targetScoreLong = Long.parseLong(targetScore);
            if (targetScoreLong <= 0) {
                throw new NumberFormatException();
            }
            TypingTargetScore typingTargetScore = new TypingTargetScore(targetScoreLong, false);
            typingTargetList.addTarget(typingTargetScore);
            typingTargets.update(typingTargetList);
            ui.showTargetAdded(typingTargetScore.getString());

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            ui.drawLine();
            ui.showErrorMessage("No/Invalid target score entered. Please provide a valid integer!");
            ui.drawLine();
        }
    }
}
