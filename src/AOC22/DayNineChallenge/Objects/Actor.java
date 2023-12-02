package AOC22.DayNineChallenge.Objects;

import Utility.Position;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Actor {

    private List<Follower> followers;

    private HashMap<Position, Boolean> visited = new HashMap<>();

    private Position position;

    protected String display;

    public Actor() {
        followers = new ArrayList<>();
        position = new Position(0, 0);
    }

    public Actor(int x, int y) {
        followers = new ArrayList<>();
        position = new Position(x, y);
        visited.put(position, true);
    }

    public Actor(String display) {
        this.display = display;
    }

    public Position getPosition() {
        return position;
    }

    public void move(int x, int y) {
        position.addX(x);
        position.addY(y);
        this.visit(position);
        this.notifyFollowers();
    }

    public void move(int[] move) {
        this.move(move[0], move[1]);
    }

    protected void subscribe(Follower follower) {
        followers.add(follower);
    }

    protected void notifyFollowers() {
        for (Follower follower : followers) {
            follower.update(this);
        }
    }

    protected int distanceTo(Actor other) {
        return Math.max(
                Math.abs(this.position.x() - other.position.x()) / 2,
                        Math.abs(this.position.y() - other.position.y()) / 2
        );
    }

    @Override
    public String toString() {
        return this.display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public void visit(Position pos) {
        this.visited.put(pos.copy(), true);
    }

    public boolean hasVisited(Position pos) {
        if (this.visited.containsKey(pos)) {
            return this.visited.get(pos);
        }
        return false;
    }

    public long totalVisited() {
        long res = 0;
        for (Boolean v : visited.values()) {
            res += v ? 1 : 0;
        }

        return res;
    }

    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }
}
