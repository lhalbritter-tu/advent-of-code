package AOC23.DayEleven.Objects;

import Utility.Position;

public class Galaxy {
    private Position position;

    public Galaxy(Position position) {
        this.position = position;
    }

    public double distanceTo(Galaxy other) {
        return position.distance(other.position);
    }

    public long stepsTo(Galaxy other) {
        return Math.abs(other.position.x() - this.position.x()) + Math.abs(other.position.y() - this.position.y());
    }

    public int x() {
        return position.x();
    }

    public int y() {
        return position.y();
    }

    public int directionX(Galaxy other) {
        return other.x() - this.x();
    }

    public int directionY(Galaxy other) {
        return other.y() - this.y();
    }

    public boolean between(Galaxy other, Position i) {
        return (this.x() - i.x()) * (i.y() - other.y()) == (i.x() - other.x()) * (this.y() - i.y());
    }
}
