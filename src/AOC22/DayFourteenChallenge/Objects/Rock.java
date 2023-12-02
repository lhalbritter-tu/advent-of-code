package AOC22.DayFourteenChallenge.Objects;

import Utility.Position;

public class Rock extends Entity {

  public Rock(int x, int y) {
    this.position = new Position(x, y);
  }

  @Override
  public void advance(Cave cave) {
    return;
  }

  @Override
  public String toString() {
    return "#";
  }
}
