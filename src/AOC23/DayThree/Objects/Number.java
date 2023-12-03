package AOC23.DayThree.Objects;

import Utility.Position;

import java.util.*;

public class Number {
  private List<Digit> digits;

  public long get() {
    String num = "";
    for (Digit digit : digits) {
      num += digit.get();
    }
    return Long.parseLong(num);
  }

  public Digit first() {
    return digits.get(0);
  }

  public Digit last() {
    return digits.get(digits.size() - 1);
  }

  public Digit get(int i) {
    return digits.get(i);
  }

  public void putLeft(Digit digit) {
    digits.add(0, digit);
  }

  public void putRight(Digit digit) {
    digits.add(digit);
  }

  public int length() {
    return digits.size();
  }

  public boolean containsPosition(Position position) {
    return digits.stream().anyMatch((digit -> digit.position().equals(position)));
  }

  public Number() {
    this.digits = new ArrayList<>();
  }
}
