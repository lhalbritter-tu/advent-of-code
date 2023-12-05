package AOC23.DayFive.Objects;

public class Seed {
    private long number;
    private long range;

    public Seed(long number, long range) {
        this.number = number;
        this.range = range;
    }

    public long number() {
        return number;
    }

    public long range() {
        return range;
    }
}
