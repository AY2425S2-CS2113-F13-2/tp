/**
 * Class representing a target score
 */

package typing;

public class TypingTargetScore extends TypingTarget {
    public TypingTargetScore(long target) {
        super(target);
    }

    /**
     * Prints a message informing the user whether their target has been hit
     */
    @Override
    public void printHit() {
        if (this.getHit()) {
            System.out.println(" Congratulations! Typing Score Target has been hit: " + this.getTarget() + " WPM");
        } else {
            System.out.println(" Almost there! Hit this target next time: " + this.getTarget() + " WPM");
        }
    }
}
