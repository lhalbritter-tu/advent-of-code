package Utility;

public class Position implements Comparable<Position> {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void addX(int x) {
        this.x += x;
    }

    public void addY(int y) {
        this.y += y;
    }

    public int x() {
        return x;
    }

    public int y() {
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
        return x * 42 + y * 69;
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

    public Position copy(int ax, int ay) {
        return new Position(this.x + ax, this.y + ay);
    }

    @Override
    public int compareTo(Position position) {
        return (this.x() - position.x()) + (this.y() - position.y());
    }

    public int distance(Position position) {
        return Math.abs(this.x() - position.x()) + Math.abs(this.y() - position.y());
    }
}
