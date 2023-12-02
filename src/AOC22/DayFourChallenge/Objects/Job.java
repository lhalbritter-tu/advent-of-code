package AOC22.DayFourChallenge.Objects;

public class Job {

    private int start;
    private int end;

    private int[] range;

    public Job(int start, int end) {
        this.start = start;
        this.end = end;
        this.range = new int[] {start, end};
    }

    public Job(String[] range) {
        this.start = Integer.parseInt(range[0].strip());
        this.end = Integer.parseInt(range[1].strip());
        this.range = new int[] {start, end};
    }

    public boolean contains(Job other) {
        return other.start >= this.start && other.end <= this.end;
    }

    public boolean overlaps(Job other) {
        for (int i : this.range) {
            if (other.contains(new Job(i, i))) return true;
        }

        return false;
    }
}
