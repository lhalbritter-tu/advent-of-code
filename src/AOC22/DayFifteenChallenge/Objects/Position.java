package AOC22.DayFifteenChallenge.Objects;

public class Position implements Comparable<Position> {
    private long x;
    private long y;

    public Position(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(long y) {
        this.y = y;
    }

    public void addX(long x) {
        this.x += x;
    }

    public void addY(long y) {
        this.y += y;
    }

    public void add(long x, long y) {
        addX(x);
        addY(y);
    }

    public long x() {
        return x;
    }

    public long y() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.getClass() != Position.class) return false;
        Position other = (Position) obj;
        return this.x == other.x && this.y == other.y;
    }

    @Override
    public int hashCode() {
        return (int) (x * 42 + y * 69);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position copy() {
        return new Position(this.x, this.y);
    }

    public Position copy(long ax, long ay) {
        return new Position(this.x + ax, this.y + ay);
    }

    @Override
    public int compareTo(Position position) {
        return (int) ((this.x() - position.x()) + (this.y() - position.y()));
    }

    public long distance(Position position) {
        return Math.abs(this.x() - position.x()) + Math.abs(this.y() - position.y());
    }

    public long getDistressSignal(long tuningRate) {
        return this.x * tuningRate + this.y;
    }
}
