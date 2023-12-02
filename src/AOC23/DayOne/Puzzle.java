package AOC23.DayOne;

import AOC23.DayOne.Objects.Calibration;
import Utility.InputManager;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
  public static void main(String[] args) {
    List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(1, 2023);
    List<String> test = List.of(("two1nine\n" +
        "eightwothree\n" +
        "abcone2threexyz\n" +
        "xtwone3four\n" +
        "4nineeightseven2\n" +
        "zoneight234\n" +
        "7pqrstsixteen").split("\n"));

    System.out.println("The calibration for the document is " + Calibration.parse(input));
  }
}
