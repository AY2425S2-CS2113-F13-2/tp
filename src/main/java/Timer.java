import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer {
    private static final Logger logger = Logger.getLogger("TimerLogger");

    static {
        logger.setLevel(Level.WARNING);
    }

    private long startTime = -1;
    private long stopTime = -1;

    public void start() {
        this.startTime = System.currentTimeMillis();
        logger.log(Level.INFO, "Timer started at: " + this.startTime);
    }

    public void stop() {
        this.stopTime = System.currentTimeMillis();
        logger.log(Level.INFO, "Timer stopped at: " + this.stopTime);
    }

    public double getDurationMin() throws IllegalStateException {
        if (startTime == -1 || stopTime == -1) {
            logger.log(Level.SEVERE, "Timer not started or stopped");
            throw new IllegalStateException("Timer not started or stopped");
        }
        double duration = (double) (this.stopTime - this.startTime) / 60000;
        assert duration >= 0.0 : "duration must be a positive number";
        return duration;
    }
}
