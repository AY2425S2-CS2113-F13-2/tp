/**
 * Class to represent a timer that can be started, stopped, and returns the elapsed time
 */

package modes;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TypingTimer {
    private static final Logger logger = Logger.getLogger("TimerLogger");

    static {
        logger.setLevel(Level.WARNING);
    }

    private long startTime = -1;
    private long stopTime = -1;

    /**
     * Start timer
     */
    public void start() {
        this.startTime = System.currentTimeMillis();
        logger.log(Level.INFO, "TypingTimer started at: " + this.startTime);
    }

    /**
     * Stop timer
     */
    public void stop() {
        this.stopTime = System.currentTimeMillis();
        logger.log(Level.INFO, "TypingTimer stopped at: " + this.stopTime);
    }

    /**
     * Get the duration for the time elapsed (duration between when timer is started and stopped)
     * @return Duration in minutes
     * @throws IllegalStateException when timer is not started or stopped
     */
    public double getDurationMin() throws IllegalStateException {
        if (startTime == -1 || stopTime == -1 || startTime >= stopTime) {
            logger.log(Level.SEVERE, "TypingTimer not started or stopped");
            throw new IllegalStateException("TypingTimer not started or stopped");
        }
        double duration = (double) (this.stopTime - this.startTime) / 60000;
        assert duration > 0.0 : "duration must be a positive number > 0";
        return duration;
    }
}
