/**
 * Class representing a list of typing targets
 */

package typing;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypingTargetList {
    private static final Logger logger = Logger.getLogger("TimerLogger");

    static {
        logger.setLevel(Level.WARNING);
    }

    private final ArrayList<TypingTarget> typingTargetList = new ArrayList<>();

    /**
     * Adds a typing target to the list
     * @param typingTarget Target speed/score
     */
    public void addTarget(TypingTarget typingTarget) {
        this.typingTargetList.add(typingTarget);
    }

    /**
     * Returns the typing target list
     * @return Typing target list
     */
    public ArrayList<TypingTarget> getTypingTargetList() {
        return this.typingTargetList;
    }

    /**
     * Prints the typing target list (for debugging)
     */
    public void print() {
        System.out.print("[");
        int index = 0;
        for (TypingTarget typingTarget : typingTargetList) {
            if (index == typingTargetList.size() - 1) {
                System.out.print(typingTarget.getClass() + ": " + typingTarget.getTarget() + " | " +
                        typingTarget.getHit());
            } else {
                System.out.print(typingTarget.getClass() + ": " + typingTarget.getTarget() + " | " +
                        typingTarget.getHit() + ", ");
                index++;
            }
        }
        System.out.println("]");
    }
}
