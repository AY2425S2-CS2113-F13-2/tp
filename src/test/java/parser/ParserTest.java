package parser;

import command.StartCommand;
import command.TypingAccuracyCommand;
import command.ExitCommand;
import command.HighscoreCommand;
import command.HighscoreListCommand;
import command.MilestoneCommand;
import command.TargetAddSpeedCommand;
import command.TargetAddScoreCommand;
import command.TargetListCommand;
import exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    @Test
    void parseToCommand_start_instantiateStartCommand() {
        assertInstanceOf(StartCommand.class, Parser.parseToCommand("start"));
    }

    @Test
    void parseToCommand_typingaccuracy_instantiateTypingAccuracyCommand() {
        assertInstanceOf(TypingAccuracyCommand.class, Parser.parseToCommand("typingaccuracy"));
    }

    @Test
    void parseToCommand_exit_instantiateExitCommand() {
        assertInstanceOf(ExitCommand.class, Parser.parseToCommand("exit"));
    }

    @Test
    void parseToCommand_highscore_instantiateHighscoreCommand() {
        assertInstanceOf(HighscoreCommand.class, Parser.parseToCommand("highscore"));
    }

    @Test
    void parseToCommand_highscorelist_instantiateHighscoreListCommand() {
        assertInstanceOf(HighscoreListCommand.class, Parser.parseToCommand("highscore list"));
    }

    @Test
    void parseToCommand_milestone_instantiateMilestoneCommand() {
        assertInstanceOf(MilestoneCommand.class, Parser.parseToCommand("milestone"));
    }

    @Test
    void parseToCommand_targetaddspeed_instantiateTargetAddSpeedCommand() {
        assertInstanceOf(TargetAddSpeedCommand.class, Parser.parseToCommand("target add speed"));
    }

    @Test
    void parseToCommand_targetaddscore_instantiateTargetAddScoreCommand() {
        assertInstanceOf(TargetAddScoreCommand.class, Parser.parseToCommand("target add score"));
    }

    @Test
    void parseToCommand_targetlist_instantiateListtargetsCommand() {
        assertInstanceOf(TargetListCommand.class, Parser.parseToCommand("target list"));
    }

    @Test
    void parseToCommand_incorrectInput_throwInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> Parser.parseToCommand("invalidinput"));
        assertThrows(InvalidInputException.class, () -> Parser.parseToCommand(""));
    }


}
