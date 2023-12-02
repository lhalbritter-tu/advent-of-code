package AOC22.DayNineChallenge.Objects;

import Utility.Position;

public class Follower extends Actor {

    public Follower() {
        super();
    }

    public Follower(int x, int y) {
        super(x, y);
    }

    public void follow(Actor a) {
        a.subscribe(this);
    }

    public void update(Actor actor) {
        Position targetPos = actor.getPosition();
        int[] move = new int[] {0, 0};
        Position followerPos = getPosition();
        int distance = distanceTo(actor);
        if (distance < 1) { return; }

        if (followerPos.x() < targetPos.x()) {
            move[0] = 1;
        } else if (followerPos.x() > targetPos.x()) {
            move[0] = -1;
        }

        if (followerPos.y() < targetPos.y()) {
            move[1] = 1;
        } else if (followerPos.y() > targetPos.y()) {
            move[1] = -1;
        }

        this.move(move);
    }
}
