package command;

import modes.CustomMode;
import modes.NormalMode;
import modes.TimeLimitMode;
import modes.ZenMode;
import modes.TypingTimer;
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
            AutoAdjust autoAdjust,
            String command
    ) throws IOException {
        while (true) {
            ui.chooseMode();
            String mode = sc.nextLine().trim();
            TextSelector textSelector = new TextSelector(sc, ui);
            List<String> testText = null;

            switch (mode) {
            case "exit":
                ui.showExit();
                System.exit(0);
                return;
            case "zen":
                ZenMode zenMode = new ZenMode(typingTimer, sc, ui);
                zenMode.startZenMode();
                break;
            case "custom":
                CustomMode customMode = new CustomMode(ui, sc);
                try {
                    customMode.startCustomMode();
                } catch (IOException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                break;

            case "timeLimit":
                testText = textSelector.selectText();
                TimeLimitMode timeLimitMode = new TimeLimitMode(ui, sc);
                try {
                    timeLimitMode.startTimeLimitMode(testText, textSelector.getDifficultyLevel());
                } catch (InterruptedException e) {
                    ui.showErrorMessage(e.getMessage());
                }
                ui.showEndGame();
                break;

            case "normal":
                testText = textSelector.selectText();

                if (testText == null || textSelector.getDifficultyLevel() == null) {
                    ui.showErrorMessage("Failed to load text or difficulty level. Please try again.");
                    continue;
                }

                String difficulty = textSelector.getDifficultyLevel().name().toLowerCase();

                NormalMode normalMode = new NormalMode(
                        ui, sc, typingTargetList, typingTargets, state, autoAdjust, typingAccuracy
                );
                normalMode.startNormalMode(testText, difficulty);

                ui.showEndGame();
                break;

            default:
                ui.showErrorMessage("Please enter a valid mode: 'normal', 'timeLimit', or 'zen' or 'custom'.");
                continue;
            }
            break;
        }
    }
}
