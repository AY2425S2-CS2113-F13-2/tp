package parser;

import command.Command;
import command.StartCommand;
import command.TypingAccuracyCommand;
import command.ExitCommand;
import command.HighscoreCommand;
import command.HighscorelistCommand;
import command.MilestoneCommand;
import command.TargetspeedaddCommand;
import command.TargetscoreaddCommand;

import exceptions.InvalidInputException;

public class Parser {

    private String userInput;

    /**
     * Constructs a UserInputParser object and sets the user input.
     * Splits user input by spaces.
     * @param userInput The complete input provided by the user.
     */
    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses user input to identify type of command
     * @return command of corresponding type
     */
    //No break as all cases return
    public Command parseToCommand() {
        switch (userInput) {
        case "start":
            return new StartCommand();
        case "typingaccuracy":
            return new TypingAccuracyCommand();
        case "exit":
            return new ExitCommand();
        case "highscore":
            return new HighscoreCommand();
        case "highscorelist":
            return new HighscorelistCommand();
        case "milestone":
            return new MilestoneCommand();
        case "targetspeedadd":
            return new TargetspeedaddCommand();
        case "targetscoreadd":
            return new TargetscoreaddCommand();
        default:
            throw new InvalidInputException("Invalid input");
        }
    }
}

