package AOC22.DaySixChallenge.Objects;

public class Duplicate {
  private char character;
  private int position;

  public Duplicate(char character, int position) {
    this.character = character;
    this.position = position;
  }

  public int distanceTo(Duplicate other) {
    return Math.abs(this.position - other.position);
  }

  public int position() {
    return position;
  }
}
