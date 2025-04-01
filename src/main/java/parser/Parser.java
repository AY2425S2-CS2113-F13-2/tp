package parser;

import command.Command;
import command.ExitCommand;
import command.HighscoreCommand;
import command.StartCommand;
import command.TypingAccuracyCommand;
import command.HighscorelistCommand;
import command.MilestoneCommand;
import command.TargetscoreaddCommand;
import command.TargetspeedaddCommand;
import command.ListtargetsCommand;

import exceptions.InvalidInputException;

public class Parser {

    private final String userInput;

    /**
     * Constructs a UserInputParser object and sets the user input.
     * Splits user input by spaces.
     *
     * @param userInput The complete input provided by the user.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses user input to identify type of command
     *
     * @return command of corresponding type
     */
    //No break as all cases return
    public Command parseToCommand() {
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
        default -> throw new InvalidInputException("Invalid input");
        };
    }
}

