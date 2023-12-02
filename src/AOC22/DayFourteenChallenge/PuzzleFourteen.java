package AOC22.DayFourteenChallenge;

import AOC22.DayFourteenChallenge.Objects.Cave;
import Utility.InputManager;

import java.util.List;

public class PuzzleFourteen {
  public static void main(String[] args) {
    List<String> input =
            InputManager.INSTANCE.getAdventOfCodeInput(14, 2022);
            //List.of(InputManager.INSTANCE.getFile(InputManager.DEFAULT_INPUT_PATH + "/resources/input/dayfourteen_example"));

    Cave cave = new Cave(input);
    System.out.println("== Part 1 ==");
    System.out.println(cave.simulate());
    System.out.println("== Part 2 ==");
    cave = new Cave(input);
    System.out.println(cave.simulateWithFloor());
  }
}
