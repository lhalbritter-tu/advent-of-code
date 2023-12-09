package AOC23.DayNine;

import AOC23.DayNine.Objects.History;
import Utility.InputManager;

import java.util.*;

public class Puzzle {
  public static void main(String[] args) {
    List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(9, 2023);
    List<String> test = List.of(("0 3 6 9 12 15\n" +
        "1 3 6 10 15 21\n" +
        "10 13 16 21 30 45").split("\n"));
    // input = test;

    List<History> histories = new ArrayList<>();
    Long sum = 0L;
    for (String line : input) {
      History history = new History(line);
      histories.add(history);
      sum += history.nextChange(false);
    }

    System.out.println(sum);

    // -- PART TWO -- \\
    sum = 0L;
    for (History history : histories) {
      Long a = history.previousChange(false);
      System.out.println(a);
      sum += a;
     }
    System.out.println(sum);
  }
}
