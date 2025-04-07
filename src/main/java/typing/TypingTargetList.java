/**
 * Class representing a list of typing targets
 */

//@@author rodi-314

package typing;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypingTargetList {
    private static final Logger logger = Logger.getLogger("TypingTargetListLogger");

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

    public TypingTarget getTarget(int targetNo) {
        return this.typingTargetList.get(targetNo - 1);
    }

    public void removeTarget(int targetNo) {
        this.typingTargetList.remove(targetNo - 1);
    }

    /**
     * Returns the typing target list
     * @return Typing target list
     */
    public ArrayList<TypingTarget> getTypingTargetList() {
        return this.typingTargetList;
    }

    public int getTargetCount() {
        return typingTargetList.size();
    }

    /**
     * Prints the typing target list
     */
    public void print() {
        System.out.println(" Here is your list of targets!");
        int count = 1;
        for (TypingTarget typingTarget: getTypingTargetList()) {
            System.out.printf(" %d.%s%n", count, typingTarget.getString());
            count++;
        }
    }
}
