package modes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import typing.MarkedText;
import util.WordCounter;
import ui.Ui;
import enums.DifficultyLevel;

class ClockThread extends Thread {
    private long startTime;
    private long elapsedTime;

    @Override
    public void run() {
        startTime = System.currentTimeMillis();

        while (!Thread.interrupted()) {
            elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    public long getElapsedTime() {
        return elapsedTime;
    }
}

public class TimeLimitMode {
    private int numOfCorrect;
    private int numOfLines;
    private WordCounter wordCounter;
    private BufferedReader reader;
    private List<String> testText;
    private Ui ui;
    private Scanner sc;

    public TimeLimitMode(Ui ui, Scanner sc) {
        this.wordCounter = new WordCounter();
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.ui = ui;
        this.sc = sc;
    }

    public void startTimeLimitMode(List<String> testText, DifficultyLevel difficulty) throws InterruptedException {
        ui.showTimeLimitModeInstructions(ui);
        numOfCorrect = 0;
        this.testText = testText;

        for (String s : testText) {
            String userInput = "";
            long timeLimit = getTimeLimit(s, difficulty);
            ClockThread clockThread = new ClockThread();

            System.out.println(s);
            clockThread.start();
            MarkedText markedText = new MarkedText();

            try {
                userInput = waitForInput(clockThread, reader, timeLimit); // Get user input
            } catch (Exception e) {
                System.out.println("An error occurred while waiting for input.");
            }

            if (userInput != null) {
                markedText.update(s, userInput);
                markedText.print();
                if (userInput.equals(s)) {
                    numOfCorrect++;
                    System.out.println("*** Great! ***");
                } else {
                    System.out.println("*** Wrong! Please be more careful next time! ***");
                }
            } else {
                System.out.println("\n*** Time's up! Try typing faster! ***");
            }
            // After time limit or correct input, interrupt the threads
            clockThread.interrupt();
        }

        ui.showTimeLimitResult(numOfLines, numOfCorrect);
        sc.nextLine();

    }

    // Non-blocking check for user input using BufferedReader and ready()
    String waitForInput(ClockThread clockThread, BufferedReader reader, long timeLimit) throws Exception {
        long startTime = System.currentTimeMillis();
        String userInput = null;

        while (clockThread.getElapsedTime() < timeLimit) {
            if (reader.ready()) { // Check if there's input available without blocking
                userInput = reader.readLine(); // Read the input
                // to prevent the program to process the input after the previous interrupt as valid input
                if (clockThread.getElapsedTime() >= 3) {
                    break;
                }
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return null;
            }
        }
        return userInput; // Return the user input or null if time runs out
    }

    public int getNumOfCorrect() {
        return numOfCorrect;
    }

    public int getNumOfLines() {
        numOfLines = testText.size();
        return numOfLines;
    }

    long getTimeLimit(String s, DifficultyLevel difficulty) {
        return (long) (wordCounter.countWords(s) / difficulty.getDivisor());
    }
}

