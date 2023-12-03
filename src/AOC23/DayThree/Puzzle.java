package AOC23.DayThree;

import AOC23.DayThree.Objects.Digit;
import AOC23.DayThree.Objects.Number;
import Utility.InputManager;
import Utility.Position;

import java.util.*;

public class Puzzle {
  public static void main(String[] args) {
    List<List<String>> input = InputManager.INSTANCE.getAdventOfCodeInput2D(3, 2023);
    //List<List<String>> input = InputManager.INSTANCE.get2D("467..114..\n" +
    //    "...*......\n" +
    //    "..35..633.\n" +
    //    "......#...\n" +
    //    "617*......\n" +
    //    ".....+.58.\n" +
    //    "..592.....\n" +
    //    "......755.\n" +
    //    "...$.*....\n" +
    //    ".664.598..");

    long sum = 0;
    for (int x = 0; x < input.size(); x++) {
      List<String> line = input.get(x);
      for (int y = 0; y < input.get(x).size(); y++) {
        String symbol = line.get(y);
        if (symbol.equals("."))
          continue;
        if (Character.isDigit(symbol.charAt(0))) {
          Digit digit = new Digit(Integer.parseInt(symbol), new Position(x, y));
          if (isPartNumber(input, digit)) {
            Number partNum = collectNumber(input, digit);
            System.out.println(partNum.get() + " is a part number.");
            sum += partNum.get();
            y = partNum.last().position().y() + 1;
          }
        }
      }
    }
    System.out.println(sum);

    sum = 0;
    for (int x = 0; x < input.size(); x++) {
      List<String> line = input.get(x);
      for (int y = 0; y < input.get(x).size(); y++) {
        String symbol = line.get(y);
        if (!symbol.equals("*"))
          continue;
        long gearRatio = getGearRatio(input, x, y);
        sum += gearRatio == -1 ? 0 : gearRatio;
      }
    }
    System.out.println(sum);
  }

  public static boolean isPartNumber(List<List<String>> input, Digit digit) {
    int x = digit.position().x();
    int y = digit.position().y();
    boolean isPart = false;

    if ((y + 1) < input.get(x).size()) {
      isPart = isPart ||
          !(input.get(x).get(y + 1).equals(".") || Character.isDigit(input.get(x).get(y + 1).charAt(0)));
      if ((x + 1) < input.size()) {
        isPart = isPart ||
            !(input.get(x + 1).get(y + 1).equals(".") || Character.isDigit(input.get(x + 1).get(y + 1).charAt(0)));
      }
      if ((x - 1) >= 0) {
        isPart = isPart ||
            !(input.get(x - 1).get(y + 1).equals(".") || Character.isDigit(input.get(x - 1).get(y + 1).charAt(0)));
      }
    }

    if ((y - 1) >= 0) {
      isPart = isPart ||
          !(input.get(x).get(y - 1).equals(".") || Character.isDigit(input.get(x).get(y - 1).charAt(0)));
      if ((x + 1) < input.size()) {
        isPart = isPart ||
            !(input.get(x + 1).get(y - 1).equals(".") || Character.isDigit(input.get(x + 1).get(y - 1).charAt(0)));
      }
      if ((x - 1) >= 0) {
        isPart = isPart ||
            !(input.get(x - 1).get(y - 1).equals(".") || Character.isDigit(input.get(x - 1).get(y - 1).charAt(0)));
      }
    }

    if ((x + 1) < input.size()) {
      isPart = isPart ||
          !(input.get(x + 1).get(y).equals(".") || Character.isDigit(input.get(x + 1).get(y).charAt(0)));
      if ((y + 1) < input.get(x + 1).size()) {
        isPart = isPart ||
            !(input.get(x + 1).get(y + 1).equals(".") || Character.isDigit(input.get(x + 1).get(y + 1).charAt(0)));
      }
      if ((y - 1) >= 0) {
        isPart = isPart ||
            !(input.get(x + 1).get(y - 1).equals(".") || Character.isDigit(input.get(x + 1).get(y - 1).charAt(0)));
      }
    }

    if ((x - 1) >= 0) {
      isPart = isPart ||
          !(input.get(x - 1).get(y).equals(".") || Character.isDigit(input.get(x - 1).get(y).charAt(0)));
      if ((y + 1) < input.get(x - 1).size()) {
        isPart = isPart ||
            !(input.get(x - 1).get(y + 1).equals(".") || Character.isDigit(input.get(x - 1).get(y + 1).charAt(0)));
      }
      if ((y - 1) >= 0) {
        isPart = isPart ||
            !(input.get(x - 1).get(y - 1).equals(".") || Character.isDigit(input.get(x - 1).get(y - 1).charAt(0)));
      }
    }

    return isPart;
  }

  public static long getGearRatio(List<List<String>> input, int x, int y) {
    Number first = null;
    Number second = null;

    if ((y + 1) < input.get(x).size()) {
      if (!(first != null && first.containsPosition(new Position(x, y + 1)))
          && !(second != null && second.containsPosition(new Position(x, y + 1))) &&
          Character.isDigit(input.get(x).get(y + 1).charAt(0))) {
        Digit toCollect = new Digit(Integer.parseInt(input.get(x).get(y + 1)), new Position(x, y + 1));
        if (first == null)
          first = collectNumber(input, toCollect);
        else if (second == null)
          second = collectNumber(input, toCollect);
        else {
          return -1;
        }
      }
      if ((x + 1) < input.size()) {
        if (!(first != null && first.containsPosition(new Position(x + 1, y + 1)))
            && !(second != null && second.containsPosition(new Position(x + 1, y + 1))) &&
            Character.isDigit(input.get(x + 1).get(y + 1).charAt(0))) {
          Digit toCollect = new Digit(Integer.parseInt(input.get(x + 1).get(y + 1)), new Position(x + 1, y + 1));
          if (first == null)
            first = collectNumber(input, toCollect);
          else if (second == null)
            second = collectNumber(input, toCollect);
          else {
            return -1;
          }
        }
      }
      if ((x - 1) >= 0) {
        if (!(first != null && first.containsPosition(new Position(x - 1, y + 1)))
            && !(second != null && second.containsPosition(new Position(x - 1, y + 1))) &&
            Character.isDigit(input.get(x - 1).get(y + 1).charAt(0))) {
          Digit toCollect = new Digit(Integer.parseInt(input.get(x - 1).get(y + 1)), new Position(x - 1, y + 1));
          if (first == null)
            first = collectNumber(input, toCollect);
          else if (second == null)
            second = collectNumber(input, toCollect);
          else {
            return -1;
          }
        }
      }
    }

    if ((y - 1) >= 0) {
      if (!(first != null && first.containsPosition(new Position(x, y - 1)))
          && !(second != null && second.containsPosition(new Position(x, y - 1))) &&
          Character.isDigit(input.get(x).get(y - 1).charAt(0))) {
        Digit toCollect = new Digit(Integer.parseInt(input.get(x).get(y - 1)), new Position(x, y - 1));
        if (first == null)
          first = collectNumber(input, toCollect);
        else if (second == null)
          second = collectNumber(input, toCollect);
        else {
          return -1;
        }
      }
      if ((x + 1) < input.size()) {
        if (!(first != null && first.containsPosition(new Position(x + 1, y - 1)))
            && !(second != null && second.containsPosition(new Position(x + 1, y - 1))) &&
            Character.isDigit(input.get(x + 1).get(y - 1).charAt(0))) {
          Digit toCollect = new Digit(Integer.parseInt(input.get(x + 1).get(y - 1)), new Position(x + 1, y - 1));
          if (first == null)
            first = collectNumber(input, toCollect);
          else if (second == null)
            second = collectNumber(input, toCollect);
          else {
            return -1;
          }
        }
      }
      if ((x - 1) >= 0) {
        if (!(first != null && first.containsPosition(new Position(x - 1, y - 1)))
            && !(second != null && second.containsPosition(new Position(x - 1, y - 1))) &&
            Character.isDigit(input.get(x - 1).get(y - 1).charAt(0))) {
          Digit toCollect = new Digit(Integer.parseInt(input.get(x - 1).get(y - 1)), new Position(x - 1, y - 1));
          if (first == null)
            first = collectNumber(input, toCollect);
          else if (second == null)
            second = collectNumber(input, toCollect);
          else {
            return -1;
          }
        }
      }
    }

    if ((x + 1) < input.size()) {
      if (!(first != null && first.containsPosition(new Position(x + 1, y)))
          && !(second != null && second.containsPosition(new Position(x + 1, y))) &&
          Character.isDigit(input.get(x + 1).get(y).charAt(0))) {
        Digit toCollect = new Digit(Integer.parseInt(input.get(x + 1).get(y)), new Position(x + 1, y));
        if (first == null)
          first = collectNumber(input, toCollect);
        else if (second == null)
          second = collectNumber(input, toCollect);
        else {
          return -1;
        }
      }
    }

    if ((x - 1) >= 0) {
      if (!(first != null && first.containsPosition(new Position(x - 1, y)))
          && !(second != null && second.containsPosition(new Position(x - 1, y))) &&
          Character.isDigit(input.get(x - 1).get(y).charAt(0))) {
        Digit toCollect = new Digit(Integer.parseInt(input.get(x - 1).get(y)), new Position(x - 1, y));
        if (first == null)
          first = collectNumber(input, toCollect);
        else if (second == null)
          second = collectNumber(input, toCollect);
        else {
          return -1;
        }
      }
    }

    if (first == null || second == null)
      return -1;
    return first.get() * second.get();
  }

  public static Number collectNumber(List<List<String>> input, Digit startDigit) {
    int x = startDigit.position().x();
    int y = startDigit.position().y();
    int startY = y;
    Number number = new Number();
    number.putLeft(startDigit);

    List<String> line = input.get(x);
    while((y - 1) >= 0 && Character.isDigit(line.get(y - 1).charAt(0))) {
      number.putLeft(
          new Digit(
              Integer.parseInt(line.get(--y)),
              new Position(x, y)
          )
      );
    }
    y = startY;
    while((y + 1) < line.size() && Character.isDigit(line.get(y + 1).charAt(0))) {
      number.putRight(
          new Digit(
              Integer.parseInt(line.get(++y)),
              new Position(x, y)
          )
      );
    }

    return number;
  }
}
