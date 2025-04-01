public enum DifficultyLevel {
    EASY(0.67),
    INTERMEDIATE(0.83),
    DIFFICULT(1.0);  // 1.0 means no change

    private final double divisor;

    DifficultyLevel(double divisor) {
        this.divisor = divisor;
    }

    public double getDivisor() {
        return divisor;
    }
}
