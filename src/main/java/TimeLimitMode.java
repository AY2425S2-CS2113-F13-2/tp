import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

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
    private static WordCounter wordCounter = new WordCounter();

    public void run(List<String> testText, String difficulty) throws InterruptedException {
        numOfCorrect = 0;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        for (String s : testText) {
            String userInput = "";
            long timeLimit = getTimeLimit(s, difficulty);
            ClockThread clockThread = new ClockThread();

            System.out.println(s);
            clockThread.start();

            try {
                userInput = waitForInput(clockThread, reader, timeLimit); // Get user input
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

    // Non-blocking check for user input using BufferedReader and ready()
    String waitForInput(ClockThread clockThread, BufferedReader reader, long timeLimit) throws Exception {
        long startTime = System.currentTimeMillis();
        String userInput = null;

        while (clockThread.getElapsedTime() < timeLimit) {
            if (reader.ready()) { // Check if there's input available without blocking
                userInput = reader.readLine(); // Read the input
//                break;
                if (clockThread.getElapsedTime() >= 3) { // to prevent the program to process the input after the previous interrupt as valid input
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

    long getTimeLimit(String s, String difficulty) {
        long timeLimit = 0;
        if (difficulty.equals("easy")) {
            timeLimit = (long) (wordCounter.countWords(s) / 0.67);
        } else if (difficulty.equals("intermediate")) {
            timeLimit = (long) (wordCounter.countWords(s) / 0.83);
        } else {
            timeLimit = wordCounter.countWords(s);
        }
        return timeLimit;
    }
}

