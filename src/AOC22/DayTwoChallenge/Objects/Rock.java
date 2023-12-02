package AOC22.DayTwoChallenge.Objects;

public class Rock extends Move {

    public static Rock rock = new Rock();

    private Rock() {
        this.score = 1;
        this.type = MoveType.ROCK;
    }

    @Override
    public int versus(Move other) {
        return this.score + (
                other.type == MoveType.SCISSORS ? 6 : (
                        other.type == MoveType.ROCK ? 3 : 0
                        )
                );
    }
}
