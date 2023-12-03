package AOC23.DayThree.Objects;

import Utility.Position;

public class Digit {
  private int digit;
  private Position position;

  public int get() {
    return digit;
  }

  public Position position() {
    return position;
  }

  public Digit(int digit, Position position) {
    this.digit = digit;
    this.position = position;
  }
}
