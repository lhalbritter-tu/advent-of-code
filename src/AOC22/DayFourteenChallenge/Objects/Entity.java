package AOC22.DayFourteenChallenge.Objects;

import Utility.Position;

public abstract class Entity implements Comparable<Entity> {
  protected Position position;

  @Override
  public int compareTo(Entity entity) {
    return position.compareTo(entity.position);
  }

  public abstract void advance(Cave cave);
}
