package parser;

//@@author ravi-viswa105

import command.Command;
import command.StartCommand;
import command.TypingAccuracyCommand;
import command.ExitCommand;
import command.HighscoreCommand;
import command.HighscorelistCommand;
import command.MilestoneCommand;
import command.TargetspeedaddCommand;
import command.TargetscoreaddCommand;
import command.ListtargetsCommand;
import command.ProgressReportCommand;

import exceptions.InvalidInputException;

public class Parser {

    /**
     * Parses user input to identify type of command
     *
     * @return command of corresponding type
     */
    //No break as all cases return
    public static Command parseToCommand(String userInput) throws InvalidInputException {
        return switch (userInput) {
        case "start" -> new StartCommand();
        case "typingaccuracy" -> new TypingAccuracyCommand();
        case "exit" -> new ExitCommand();
        case "highscore" -> new HighscoreCommand();
        case "highscorelist" -> new HighscorelistCommand();
        case "milestone" -> new MilestoneCommand();
        case "targetspeedadd" -> new TargetspeedaddCommand();
        case "targetscoreadd" -> new TargetscoreaddCommand();
        case "listtargets" -> new ListtargetsCommand();
        case "progress" -> new ProgressReportCommand();
        default -> throw new InvalidInputException("Invalid input");
        };
    }
}

