package typing;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

class ClockThread extends Thread {
    private long startTime; // Variable to store the start time
    private long elapsedTime; // Variable to store elapsed time in seconds

    @Override
    public void run() {
        startTime = System.currentTimeMillis(); // Store the current time as start time

        while (!Thread.interrupted()) { // Check if thread is interrupted
            // Calculate elapsed time in seconds
            elapsedTime = (System.currentTimeMillis() - startTime) / 1000; // Convert to seconds
            try {
                Thread.sleep(1000); // Sleep for 1 second before updating the time again
            } catch (InterruptedException e) {
                return; // Exit the thread when interrupted
            }
        }
    }

    // Method to get the elapsed time in seconds
    public long getElapsedTime() {
        return elapsedTime;
    }
}

public class TimeLimitMode {
    private int numOfCorrect;

    public void run(List<String> testText, long timeLimit, Scanner sc) throws InterruptedException {
        numOfCorrect = 0;
        for (String s : testText) {
            String userInput = "";
            ClockThread clockThread = new ClockThread();
            // to make sure the user input is empty before the target string is printed
            try {
                if (System.in.available() > 0) {
                    sc.nextLine(); // Consume and ignore the previous input before the game starts
                }
            } catch (IOException e) {
                System.err.println("Failed to clear input buffer: " + e.getMessage());
            }
            System.out.println(s);
            clockThread.start();

            try {
                userInput = waitForInput(clockThread, sc, timeLimit);
            } catch (Exception e) {
                System.out.println("An error occurred while waiting for input.");
            }
            if (userInput != null) {
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
    }

    private String waitForInput(ClockThread clockThread, Scanner sc, long timeLimit) throws Exception {
        while (clockThread.getElapsedTime() < timeLimit) {
            try {
                if (System.in.available() > 0) {
                    return sc.nextLine();
                }
            } catch (IOException e) {
                return null;
            }
            try {
                Thread.sleep(100); // Check every 0.1 sec
            } catch (InterruptedException e) {
                return null;
            }
        }
        return null;
    }

    public int getNumOfCorrect() {
        return numOfCorrect;
    }
}

