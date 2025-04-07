package parser;

//@@author ravi-viswa105

import command.*;

import exceptions.InvalidInputException;

public class Parser {

    /**
     * Parses user input to identify type of command
     *
     * @return command of corresponding type
     */
    //No break as all cases return
    public static Command parseToCommand(String userInput) throws InvalidInputException {
        switch (userInput) {
        case "start":
            return new StartCommand();
        case "typingaccuracy":
            return new TypingAccuracyCommand();
        case "exit":
            return new ExitCommand();
        case "highscore":
            return new HighscoreCommand();
        case "highscore list":
            return new HighscorelistCommand();
        case "milestone":
            return new MilestoneCommand();
        case "progress":
            return new ProgressReportCommand();
        case "target list":
            return new TargetListCommand();
        default:
            if (userInput.startsWith("target add speed")) {
                return new TargetAddSpeedCommand();
            } else if (userInput.startsWith("target add score")) {
                return new TargetAddScoreCommand();
            } else if (userInput.startsWith("target remove")) {
                return new TargetRemoveCommand();
            }
            throw new InvalidInputException("Invalid input");
        }
    }
}

