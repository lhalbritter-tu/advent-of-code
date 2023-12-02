package AOC22.DayTwoChallenge.Objects;

public abstract class Move {
    protected int score;
    protected MoveType type;

    public int score() {
        return score;
    }

    public abstract int versus(Move other);

    public MoveType type() {
        return this.type;
    }

    public enum MoveType {
        ROCK,
        PAPER,
        SCISSORS
    }
}
