package AOC22.DayTwoChallenge;

import AOC22.DayTwoChallenge.Objects.Move;
import AOC22.DayTwoChallenge.Objects.Paper;
import AOC22.DayTwoChallenge.Objects.Rock;
import AOC22.DayTwoChallenge.Objects.Scissors;
import Utility.InputManager;

public class PuzzleTwo {

    public static Move translateMove(char move) {
        if (move == 'A' || move == 'X') {
            return Rock.rock;
        } else if (move == 'B' || move == 'Y') {
            return Paper.paper;
        } else {
            return Scissors.scissors;
        }
    }

    public static Move translateMove(char strategy, Move otherMove) {
        switch (otherMove.type()) {
            case PAPER -> {
                return strategy == 'X' ? Rock.rock :
                        (strategy == 'Y' ? Paper.paper : Scissors.scissors);
            }
            case ROCK -> {
                return strategy == 'X' ? Scissors.scissors :
                        (strategy == 'Y' ? Rock.rock : Paper.paper);
            }
            default -> {
                return strategy == 'X' ? Paper.paper :
                        (strategy == 'Y' ? Scissors.scissors : Rock.rock);
            }
        }
    }

    public static void main(String[] args) {
        String[] lines = InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/daytwo_example");
        Long total = 0L;
        for (String line : lines) {
            String[] splitted = line.split(" ");
            Move opponent = translateMove(splitted[0].strip().charAt(0));
            Move you = translateMove(splitted[1].strip().charAt(0));
            total += you.versus(opponent);
        }

        System.out.println("Assumed: " + total);

        total = 0L;
        for (String line : lines) {
            String[] splitted = line.split(" ");
            Move opponent = translateMove(splitted[0].strip().charAt(0));
            Move you = translateMove(splitted[1].strip().charAt(0), opponent);
            total += you.versus(opponent);
        }

        System.out.println("Actual: " + total);
    }
}
