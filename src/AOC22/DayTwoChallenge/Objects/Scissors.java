package AOC22.DayTwoChallenge.Objects;

public class Scissors extends Move {

    public static Scissors scissors = new Scissors();

    private Scissors() {
        this.score = 3;
        this.type = MoveType.SCISSORS;
    }

    @Override
    public int versus(Move other) {
        return this.score + (
                other.type == MoveType.PAPER ? 6 : (
                        other.type == MoveType.SCISSORS ? 3 : 0
                        )
                );
    }
}
