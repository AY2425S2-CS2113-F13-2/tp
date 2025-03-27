abstract class TypingTarget {
    private final long target;
    private boolean hit;

    TypingTarget(long target) {
        this.target = target;
    }

    public long getTarget() {
        return this.target;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean getHit() {
        return this.hit;
    }

    public void printHit() {
    }
}
