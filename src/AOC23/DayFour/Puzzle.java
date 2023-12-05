package AOC23.DayFour;

import AOC23.DayFour.Objects.Scratchcard;
import Utility.InputManager;
import java.util.*;

public class Puzzle {
  public static void main(String[] args) {
    List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(4, 2023);
    List<String> test = List.of(("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53\n" +
        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19\n" +
        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1\n" +
        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83\n" +
        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36\n" +
        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11").split("\n"));

    long sum = 0;
    for (String card : input) {
      long value = new Scratchcard(card).value();
      // System.out.println(card + " is " + value + " points worth.");
      sum += value;
    }
    System.out.println(sum);

    Map<Scratchcard, Long> scratchcards = new TreeMap<>();
    List<Scratchcard> scratchcardList = new ArrayList<>();
    for (String card : input) {
      Scratchcard scratchcard = new Scratchcard(card);
      scratchcardList.add(scratchcard);
      scratchcards.put(scratchcard, 1L);
    }

    for (Scratchcard scratchcard : scratchcardList) {
      long executions = scratchcards.get(scratchcard);
      System.out.println(scratchcard.card() + " has " + executions + " executions.");
      scratchcard.real(scratchcards, scratchcardList, executions);
    }
    System.out.println(scratchcards.values().stream().mapToLong(Long::longValue).sum());

  }
}