package AOC23.DayTwo;

import AOC23.DayTwo.Objects.Game;
import AOC23.DayTwo.Objects.Subset;
import Utility.InputManager;

import java.util.*;

public class Puzzle {

  public static void main(String[] args) {
    List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(2, 2023);
    List<String> test = List.of(("Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green\n" +
        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue\n" +
        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red\n" +
        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red\n" +
        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green").split("\n"));

    List<Game> games = input.stream().map(Game::new).toList();

    int sumValid = 0;
    int maxRed = 12;
    int maxGreen = 13;
    int maxBlue = 14;
    for (Game game : games) {
      if (game.isValid(maxRed, maxGreen, maxBlue)) {
        System.out.println(game);
        sumValid += game.getId();
      }
    }

    System.out.println(sumValid);

    int sumPower = 0;
    for (Game game : games) {
      Subset minSet = game.minSet();
      System.out.println(minSet + " has power " + minSet.power());
      sumPower += minSet.power();
    }
    System.out.println(sumPower);
  }

}
