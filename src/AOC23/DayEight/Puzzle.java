package AOC23.DayEight;

import Utility.InputManager;
import AOC23.DayEight.Objects.Point;
import Utility.Maths;

import java.util.*;
import java.util.List;

public class Puzzle {
  public static void main(String[] args) {
    List<String> input = InputManager.INSTANCE.getAdventOfCodeInput(8, 2023);
    List<String> test = List.of(("LR\n" +
        "\n" +
        "11A = (11B, XXX)\n" +
        "11B = (XXX, 11Z)\n" +
        "11Z = (11B, XXX)\n" +
        "22A = (22B, XXX)\n" +
        "22B = (22C, 22C)\n" +
        "22C = (22Z, 22Z)\n" +
        "22Z = (22B, 22B)\n" +
        "XXX = (XXX, XXX)").split("\n"));

    // input = test;

    String instructions = input.get(0).strip();
    String start = "AAA";

    for (String line : input) {
      if (line.strip().equals(instructions))
        continue;
      if (line.isBlank())
        continue;

      String[] instruction = line.split(" = ");
      Point point;
      if ((point = Point.MAP.getOrDefault(instruction[0], null)) == null) {
        point = new Point(instruction);
        Point.MAP.put(instruction[0], point);
      } else {
        point.setNeighbours(instruction[1].split(","));
      }
    }

    System.out.println(Point.MAP.toString());

    long steps = 0;
    Point currentPoint = Point.MAP.getOrDefault(start, null);
    int currentInstruction = 0;
    while (currentPoint != null && !currentPoint.getName().equals("ZZZ")) {
      char instruction = instructions.charAt(currentInstruction);
      if (instruction == 'L')
        currentPoint = currentPoint.left();
      if (instruction == 'R')
        currentPoint = currentPoint.right();

      currentInstruction = (currentInstruction + 1) % instructions.length();
      steps++;
    }

    System.out.println("I took " + steps + " steps to reach ZZZ.");

    // -- PART TWO -- \\
    List<Point> startingPoints = new ArrayList<>();
    for (Point point : Point.MAP.values()) {
      if (point.getName().endsWith("A")) {
        startingPoints.add(point);
      }
    }

    System.out.println("I assembled all points: " + startingPoints);

    currentInstruction = 0;
    List<Long> totalSteps = new ArrayList<>();
    for (Point point : startingPoints) {
      long pointSteps = 0;
      currentInstruction = 0;
      while (!point.getName().endsWith("Z")) {
        char instruction = instructions.charAt(currentInstruction);
        if (instruction == 'L')
          point = point.left();
        if (instruction == 'R')
          point = point.right();
        currentInstruction = (currentInstruction + 1) % instructions.length();
        pointSteps++;
      }

      totalSteps.add(pointSteps);
      System.out.println(pointSteps);
    }
    steps = totalSteps.get(0);
    for (int i = 1; i < totalSteps.size(); i++) {
      steps = Maths.lcm(steps, totalSteps.get(i));
    }

    System.out.println("I took " + steps + " steps to reach ZZZ.");

    /**while (!startingPoints.stream().allMatch((point) -> point.getName().endsWith("Z"))) {
      char instruction = instructions.charAt(currentInstruction);
      for (int i = 0; i < startingPoints.size(); i++) {
        Point point = startingPoints.get(i);
        if (instruction == 'L')
          startingPoints.set(i, point.left());
        if (instruction == 'R')
          startingPoints.set(i, point.right());
      }
      // System.out.println(startingPoints.size());
      steps++;
      currentInstruction = (currentInstruction + 1) % instructions.length();
    }

    System.out.println("I took " + steps + " steps to reach ZZZ.");*/
  }
}
