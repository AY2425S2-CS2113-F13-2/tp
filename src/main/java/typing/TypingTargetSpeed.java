package typing;

public class TypingTargetSpeed extends TypingTarget {
    public TypingTargetSpeed(long target) {
        super(target);
    }

    @Override
    public void printHit() {
        if (this.getHit()) {
            System.out.println(" Congratulations! Typing Speed Target has been hit: " + this.getTarget() + " WPM");
        } else {
            System.out.println(" Almost there! Hit this target next time: " + this.getTarget() + " WPM");
        }
    }
}
