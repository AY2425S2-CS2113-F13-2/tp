/**
 * Abstract class representing a typing target
 */

package typing;

public abstract class TypingTarget {
    private final long target;
    private boolean hit;

    TypingTarget(long target) {
        this.target = target;
    }

    /**
     * Get value of target
     * @return Target value
     */
    public long getTarget() {
        return this.target;
    }

    /**
     * Set whether target has been hit
     * @param hit Boolean denoting whether target has been hit
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
     * Get boolean denoting whether target has been hit
     * @return Boolean denoting whether target has been hit
     */
    public boolean getHit() {
        return this.hit;
    }

    /**
     * Prints a message informing the user whether their target has been hit
     */
    public void printHit() {
    }
}
