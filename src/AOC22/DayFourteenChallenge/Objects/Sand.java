package AOC22.DayFourteenChallenge.Objects;

import Utility.Position;

public class Sand extends Entity {

  private boolean finished, voided;

  public Sand(int x, int y) {
    this.position = new Position(x, y);
    finished = false;
  }

  @Override
  public void advance(Cave cave) {
    if (finished) return;
    Position newPos = this.position.copy();
    newPos.setY(newPos.y() + 1);

    if (cave.isFree(newPos)) {
      this.position = newPos;
      voided = cave.isVoid(this.position);
      return;
    }

    newPos.setX(newPos.x() - 1);

    if (cave.isFree(newPos)) {
      this.position = newPos;
      voided = cave.isVoid(this.position);
      return;
    }

    newPos.setX(this.position.x() + 1);

    if (cave.isFree(newPos)) {
      this.position = newPos;
      voided = cave.isVoid(this.position);
      return;
    }

    finished = true;
    //System.out.println(cave);
  }

  public boolean isFinished() {
    return finished;
  }

  public boolean isVoided() {
    return voided;
  }

  @Override
  public String toString() {
    return "o";
  }
}
