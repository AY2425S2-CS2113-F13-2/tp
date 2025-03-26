import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TimerTest {

    @Test
    public void getDurationMin_timerNotStartedOrStopped_exceptionThrown() {
        // Timer not started and not stopped
        try {
            assertEquals(0, new Timer().getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Timer not started or stopped", e.getMessage());
        }

        // Timer not started
        try {
            Timer timer = new Timer();
            timer.stop();
            assertEquals(0, timer.getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Timer not started or stopped", e.getMessage());
        }

        // Timer not stopped
        try {
            Timer timer = new Timer();
            timer.start();
            assertEquals(0, timer.getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Timer not started or stopped", e.getMessage());
        }

        // Timer not stopped
        try {
            Timer timer = new Timer();
            timer.start();
            timer.stop();
            timer.start();
            assertEquals(0, timer.getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("Timer not started or stopped", e.getMessage());
        }
    }

}
