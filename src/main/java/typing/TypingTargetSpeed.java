/**
 * Class representing a target speed
 */

package typing;

public class TypingTargetSpeed extends TypingTarget {
    public TypingTargetSpeed(long target, boolean hit) {
        super(target, hit);
    }

    /**
     * Prints a message informing the user whether their target has been hit
     */
    @Override
    public void printHit() {
        if (this.getHit()) {
            System.out.println(" Congratulations! Typing Speed Target has been hit: " + this.getTarget() + " WPM");
        } else {
            System.out.println(" Almost there! Hit this target next time: " + this.getTarget() + " WPM");
        }
    }

    /**
     * Gets a string representation of the target
     * @return String representation of target
     */
    @Override
    public String getString() {
        return " Target Speed (WPM): " + getTarget() + " | " + (getHit() ? "Achieved" : "Not Achieved");
    }
}
