package parser;

import command.*;
import exceptions.BoboTypeException;
import exceptions.FileProcessingException;
import exceptions.InvalidInputException;
import modes.TimeLimitMode;
import modes.TypingTimer;
import modes.ZenMode;
import storage.AutoAdjust;
import storage.Milestones;
import storage.State;
import storage.Storage;
import typing.*;
import ui.Ui;
import util.RandNumGenerator;
import util.WordCounter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private String userInput;

    public static String[] parseCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Constructs a UserInputParser object and sets the user input
     * Splits user input by spaces
     * @param userInput The complete input provided by the user
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

