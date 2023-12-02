package AOC22.DayTwoChallenge.Objects;

public class Paper extends Move {

    public static Paper paper = new Paper();

    private Paper() {
        this.score = 2;
        this.type = MoveType.PAPER;
    }

    @Override
    public int versus(Move other) {
        return this.score + (
                other.type == MoveType.ROCK ? 6 : (
                        other.type == MoveType.PAPER ? 3 : 0
                        )
                );
    }
}
