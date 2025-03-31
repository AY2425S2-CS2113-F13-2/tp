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
            default -> throw new InvalidInputException("Invalid input");
        };
    }
}

