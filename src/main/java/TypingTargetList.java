import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@SuppressWarnings("ClassEscapesDefinedScope")
public class TypingTargetList {
    private static final Logger logger = Logger.getLogger("TimerLogger");

    static {
        logger.setLevel(Level.WARNING);
    }

    private ArrayList<TypingTarget> typingTargetList = new ArrayList<>();

    public void addTarget(TypingTarget typingTarget) {
        this.typingTargetList.add(typingTarget);
    }

    public void print() {
        System.out.print("[");
        int index = 0;
        for (TypingTarget typingTarget: typingTargetList) {
            if (index == typingTargetList.size() - 1) {
                System.out.print(typingTarget.getClass() + ": " + typingTarget.getTarget());
            } else {
                System.out.print(typingTarget.getClass() + ": " + typingTarget.getTarget() + ", ");
                index++;
            }
        }
        System.out.println("]");
    }
}
