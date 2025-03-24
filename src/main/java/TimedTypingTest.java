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

class UserInputThread extends Thread {
    private String targetString;
    private Scanner scanner;
    private boolean correctInput = false;

    public UserInputThread(String targetString) {
        this.targetString = targetString;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) { // Check if thread is interrupted
            System.out.println(targetString);
            String userInput = scanner.nextLine(); // Read user input

            // If user types the correct string, exit the thread
            if (userInput.equals(targetString)) {
                correctInput = true;
                System.out.println("*** GREAT! ***");
                break;
            } else {
                System.out.println("*** Incorrect input. Try again.***");
            }
        }
    }

    // Return whether user input was correct
    public boolean isCorrectInput() {
        return correctInput;
    }
}
public class TimedTypingTest {

    public static void run(List<String> testText, long timeLimit) throws InterruptedException {
        for (String s : testText) {
            ClockThread clockThread = new ClockThread();
            clockThread.start();

            UserInputThread userInputThread = new UserInputThread(s);
            userInputThread.start();

            // Main thread checks if time limit is exceeded
            while (clockThread.getElapsedTime() < timeLimit) {
                // If user typed correctly, move to next iteration
                if (userInputThread.isCorrectInput()) {
                    break; // Exit if the user typed correctly
                }
                Thread.sleep(1000); // Check every second
            }

            // After time limit or correct input, interrupt the threads
            clockThread.interrupt();
            userInputThread.interrupt();
            if (!userInputThread.isCorrectInput()) {
                System.out.println("\n *** Time's up! Try typing faster and more accurately! ***");
            }
        }
    }
}
