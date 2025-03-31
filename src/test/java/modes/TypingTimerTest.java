import org.junit.jupiter.api.Test;
import modes.TypingTimer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TypingTimerTest {

    @Test
    public void getDurationMin_timerNotStartedOrStopped_exceptionThrown() {
        // TypingTimer not started and not stopped
        try {
            assertEquals(0, new TypingTimer().getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("TypingTimer not started or stopped", e.getMessage());
        }

        // TypingTimer not started
        try {
            TypingTimer typingTimer = new TypingTimer();
            typingTimer.stop();
            assertEquals(0, typingTimer.getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("TypingTimer not started or stopped", e.getMessage());
        }

        // TypingTimer not stopped
        try {
            TypingTimer typingTimer = new TypingTimer();
            typingTimer.start();
            assertEquals(0, typingTimer.getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("TypingTimer not started or stopped", e.getMessage());
        }

        // TypingTimer not stopped
        try {
            TypingTimer typingTimer = new TypingTimer();
            typingTimer.start();
            typingTimer.stop();
            typingTimer.start();
            assertEquals(0, typingTimer.getDurationMin());
            fail();
        } catch (IllegalStateException e) {
            assertEquals("TypingTimer not started or stopped", e.getMessage());
        }
    }

}
