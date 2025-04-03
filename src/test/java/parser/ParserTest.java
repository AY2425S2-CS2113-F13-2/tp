package parser;

import command.*;
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
    void parseToCommand_highscorelist_instantiateHighscorelistCommand() {
        assertInstanceOf(HighscorelistCommand.class, Parser.parseToCommand("highscorelist"));
    }

    @Test
    void parseToCommand_milestone_instantiateMilestoneCommand() {
        assertInstanceOf(MilestoneCommand.class, Parser.parseToCommand("milestone"));
    }

    @Test
    void parseToCommand_targetspeedadd_instantiateTargetspeedaddCommand() {
        assertInstanceOf(TargetspeedaddCommand.class, Parser.parseToCommand("targetspeedadd"));
    }

    @Test
    void parseToCommand_targetscoreadd_instantiateTargetscoreaddCommand() {
        assertInstanceOf(TargetscoreaddCommand.class, Parser.parseToCommand("targetscoreadd"));
    }

    @Test
    void parseToCommand_listtargets_instantiateListtargetsCommand() {
        assertInstanceOf(ListtargetsCommand.class, Parser.parseToCommand("listtargets"));
    }

    @Test
    void parseToCommand_incorrectInput_throwInvalidInputException() {
        assertThrows(InvalidInputException.class, () -> Parser.parseToCommand("invalidinput"));
        assertThrows(InvalidInputException.class, () -> Parser.parseToCommand(""));
    }


}