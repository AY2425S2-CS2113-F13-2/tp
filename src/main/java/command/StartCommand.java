package command;

import modes.TimeLimitMode;
import modes.TypingTimer;
import modes.NormalMode;
import modes.ZenMode;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.TypingTargets;
import typing.TypingAccuracy;
import typing.TypingTargetList;
import util.TextSelector;
import ui.Ui;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StartCommand extends Command {

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
        ui.chooseMode();
        String mode = sc.nextLine().trim();
        if (mode.equals("zen")) {
            ZenMode zenMode = new ZenMode(typingTimer, sc, ui);
            zenMode.startZenMode();
        } else {
            TextSelector textSelector = new TextSelector(sc, ui);
            List<String> testText = textSelector.selectText();
            // time limit mode
            if (mode.equals("timeLimit")) {
                TimeLimitMode timeLimitMode = new TimeLimitMode(ui, sc);
                try {
                    timeLimitMode.startTimeLimitMode(testText, textSelector.getDifficultyLevel());
                } catch (InterruptedException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            } else { // normal mode
                // TODO: if (mode.equals("normal") else ask for valid input
                NormalMode normalMode = new NormalMode(
                        ui, sc, typingTargetList, typingTargets, state, autoAdjust, typingAccuracy
                );
                normalMode.startNormalMode(testText);
            }

            ui.showEndGame();
        }
    }
}
